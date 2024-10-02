package ru.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.StudentServiceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping ("/student")
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping ("{id}")
    public ResponseEntity <Student> getStudent (@PathVariable long id) {
       Student student =  studentService.findStudent(id);
       if (student == null) {
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student addStudent (@RequestBody Student student) {

        return studentService.addStudent(student);
    }
    @PutMapping
    public ResponseEntity <Student> editStudent (@RequestBody Student student) {
       Student editStudent = studentService.editStudent(student);
       if (editStudent == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
        return ResponseEntity.ok(editStudent);
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<Student> expelStudent (@PathVariable long id) {
        studentService.expelStudent(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
