package com.kiet.studentskillmanagementq3.repository;
import com.kiet.studentskillmanagementq3.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class StudentRepository {
 private final JdbcTemplate jdbc;
 public StudentRepository(JdbcTemplate jdbc){this.jdbc=jdbc;}
 private List<String> skills(int id){
  return jdbc.queryForList("SELECT skill_name FROM student_skills WHERE student_id=? ORDER BY skill_name",String.class,id);
 }
 private final RowMapper<Student> mapper=(rs,n)->{
  Student s=new Student();
  s.setStudentId(rs.getInt("student_id")); s.setStudentName(rs.getString("student_name"));
  s.setEmail(rs.getString("email")); s.setCourseName(rs.getString("course_name"));
  s.setProgrammingScore(rs.getDouble("programming_score")); s.setMathematicsScore(rs.getDouble("mathematics_score"));
  s.setTechnicalSkills(skills(s.getStudentId())); return s;
 };
 public void add(Student s){
  jdbc.update("INSERT INTO students(student_id,student_name,email,course_name,programming_score,mathematics_score) VALUES(?,?,?,?,?,?)",
   s.getStudentId(),s.getStudentName(),s.getEmail(),s.getCourseName(),s.getProgrammingScore(),s.getMathematicsScore());
  replaceSkills(s.getStudentId(),s.getTechnicalSkills());
 }
 private void replaceSkills(int id,List<String> list){
  jdbc.update("DELETE FROM student_skills WHERE student_id=?",id);
  if(list!=null) for(String skill:list) if(skill!=null&&!skill.isBlank())
   jdbc.update("INSERT INTO student_skills(student_id,skill_name) VALUES(?,?)",id,skill.trim());
 }
 public List<Student> all(){return jdbc.query("SELECT * FROM students ORDER BY student_id",mapper);}
 public Student byId(int id){
  List<Student> found=jdbc.query("SELECT * FROM students WHERE student_id=?",mapper,id);
  return found.isEmpty()?null:found.get(0);
 }
 public void update(int id,Student s){
  jdbc.update("UPDATE students SET programming_score=?,mathematics_score=? WHERE student_id=?",s.getProgrammingScore(),s.getMathematicsScore(),id);
  replaceSkills(id,s.getTechnicalSkills());
 }
 public void delete(int id){jdbc.update("DELETE FROM students WHERE student_id=?",id);}
 public List<Student> merit(){
  return jdbc.query("SELECT * FROM students ORDER BY (programming_score+mathematics_score)/2.0 DESC, student_id ASC",mapper);
 }
}
