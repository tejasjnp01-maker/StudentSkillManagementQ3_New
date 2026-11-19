# StudentSkillManagementQ3

Q3 Spring Boot REST API project using Java, Spring Web, Spring JDBC and MySQL.

## Before running
1. Start MySQL.
2. Make sure the Q2 database `StudentSkillManagementSystem` exists with `students` and `student_skills` tables.
3. Open `src/main/resources/application.properties` and replace `CHANGE_ME` with your MySQL password.
4. Open this folder in IntelliJ IDEA and wait for Maven to download dependencies.
5. Run `StudentSkillManagementQ3Application.java`.

Server: `http://localhost:8080`

## Postman endpoints
Base URL: `http://localhost:8080/api/students`

- POST `/api/students` — add student
- GET `/api/students` — retrieve all students
- GET `/api/students/{id}` — search by ID
- PUT `/api/students/{id}` — update scores and skills
- DELETE `/api/students/{id}` — delete student
- GET `/api/students/merit-list` — merit list

POST JSON:
```json
{
  "studentId": 106,
  "studentName": "Neha Sharma",
  "email": "neha@gmail.com",
  "courseName": "B.Tech CSE",
  "programmingScore": 90,
  "mathematicsScore": 95,
  "technicalSkills": ["Java", "Python", "Spring Boot", "SQL"]
}
```

PUT JSON:
```json
{
  "programmingScore": 95,
  "mathematicsScore": 98,
  "technicalSkills": ["Java", "Spring Boot", "MySQL", "React"]
}
```

Scores are validated from 0 to 100. Duplicate primary keys/emails are rejected by MySQL and returned as HTTP 409.
