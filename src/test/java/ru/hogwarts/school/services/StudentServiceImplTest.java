package ru.hogwarts.school.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentServiceImplTest {
    private final StudentServiceImpl studentService = new StudentServiceImpl();

    private final Map<Long, Student> students = new HashMap<>();
    private long lastId = 0;
    Student student = new Student(1L, "Гарри", 17);

    @Test
    @DisplayName("Добавляет студента")
    void addStudent() {
        students.put(++lastId, student);

        int actualSize = studentService.students.size();
        int expectedSize = studentService.students.size();

        assertEquals(expectedSize,actualSize);

    }

    @Test
    @DisplayName("Поиск студента")
    void findStudent() {

    }

    @Test
    @DisplayName("Изменение студента")
    void editStudent() {

    }

    @Test
    @DisplayName("Отчисление студента")
    void expelStudent() {
    }

    @Test
    @DisplayName("Поиск по возрасту")
    void findByAge() {
    }


    public long getLastId() {
        return lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
}