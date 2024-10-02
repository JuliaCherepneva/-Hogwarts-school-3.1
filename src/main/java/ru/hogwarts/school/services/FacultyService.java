package ru.hogwarts.school.services;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Faculty createFaculty (Faculty faculty);
    Faculty findFaculty (long id);
    Faculty editFaculty (Faculty faculty);
    void deleteFaculty (long id);
    Collection<Faculty> findByColor(String color);


}
