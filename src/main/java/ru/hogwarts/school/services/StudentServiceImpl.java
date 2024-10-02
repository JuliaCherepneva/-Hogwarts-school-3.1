package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;



@Service
public class StudentServiceImpl implements StudentService {
    final Map<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student addStudent (Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent (long id) {
        return students.get(id);
    }

    public Student editStudent (Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student expelStudent (long id) {
        return students.remove(id);
    }
    public Collection <Student> findByAge(int age) {
        List <Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
   }

}
