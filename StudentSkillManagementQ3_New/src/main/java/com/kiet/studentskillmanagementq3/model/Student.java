package com.kiet.studentskillmanagementq3.model;
import java.util.List;
public class Student {
 private int studentId;
 private String studentName;
 private String email;
 private String courseName;
 private double programmingScore;
 private double mathematicsScore;
 private List<String> technicalSkills;
 public Student() {}
 public Student(int studentId, String studentName, String email, String courseName, double programmingScore, double mathematicsScore, List<String> technicalSkills) {
  this.studentId=studentId; this.studentName=studentName; this.email=email; this.courseName=courseName;
  this.programmingScore=programmingScore; this.mathematicsScore=mathematicsScore; this.technicalSkills=technicalSkills;
 }
 public int getStudentId(){return studentId;} public void setStudentId(int v){studentId=v;}
 public String getStudentName(){return studentName;} public void setStudentName(String v){studentName=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getCourseName(){return courseName;} public void setCourseName(String v){courseName=v;}
 public double getProgrammingScore(){return programmingScore;} public void setProgrammingScore(double v){programmingScore=v;}
 public double getMathematicsScore(){return mathematicsScore;} public void setMathematicsScore(double v){mathematicsScore=v;}
 public List<String> getTechnicalSkills(){return technicalSkills;} public void setTechnicalSkills(List<String> v){technicalSkills=v;}
 public double getAverageScore(){return (programmingScore+mathematicsScore)/2.0;}
 public String getPerformanceCategory(){
  double a=getAverageScore(); if(a>=85)return "Excellent"; if(a>=70)return "Good"; if(a>=50)return "Satisfactory"; return "Needs Improvement";
 }
 public String getStrongerSubject(){
  if(programmingScore>mathematicsScore)return "Programming";
  if(mathematicsScore>programmingScore)return "Mathematics";
  return "Both are Equal";
 }
}
