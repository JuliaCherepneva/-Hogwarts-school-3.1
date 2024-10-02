package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty (Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty (long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty (Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty (long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> findByColor(String color) {
        List <Faculty> result = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }

}
