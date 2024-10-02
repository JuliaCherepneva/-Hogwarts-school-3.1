package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;



@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent (Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent (long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent (Student student) {
        return studentRepository.save(student);
    }

    public void expelStudent (long id) {
         studentRepository.deleteById(id);
    }
    public Collection <Student> findByAge(int age) {
        return studentRepository.findByAge(age);

   }

}
