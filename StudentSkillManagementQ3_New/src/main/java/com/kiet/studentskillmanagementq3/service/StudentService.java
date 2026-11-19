package com.kiet.studentskillmanagementq3.service;
import com.kiet.studentskillmanagementq3.model.Student;
import com.kiet.studentskillmanagementq3.repository.StudentRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@Service
public class StudentService {
 private final StudentRepository repo;
 public StudentService(StudentRepository repo){this.repo=repo;}
 @Transactional public Student add(Student s){
  validateNew(s);
  try{repo.add(s); return repo.byId(s.getStudentId());}
  catch(DuplicateKeyException e){throw new ResponseStatusException(HttpStatus.CONFLICT,"Student ID or email already exists.");}
 }
 public List<Student> all(){return repo.all();}
 public Student byId(int id){
  Student s=repo.byId(id); if(s==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found."); return s;
 }
 @Transactional public Student update(int id,Student s){
  validateScores(s.getProgrammingScore(),s.getMathematicsScore());
  if(repo.byId(id)==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found.");
  repo.update(id,s); return repo.byId(id);
 }
 @Transactional public void delete(int id){
  if(repo.byId(id)==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found.");
  repo.delete(id);
 }
 public List<Student> merit(){return repo.merit();}
 private void validateNew(Student s){
  if(s.getStudentId()<=0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Student ID must be positive.");
  if(s.getStudentName()==null||s.getStudentName().isBlank()||s.getEmail()==null||s.getEmail().isBlank()||s.getCourseName()==null||s.getCourseName().isBlank())
   throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name, email and course are required.");
  validateScores(s.getProgrammingScore(),s.getMathematicsScore());
 }
 private void validateScores(double p,double m){
  if(p<0||p>100||m<0||m>100)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Scores must be between 0 and 100.");
 }
}
