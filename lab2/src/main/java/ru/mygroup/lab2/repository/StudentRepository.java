package ru.mygroup.lab2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mygroup.lab2.Student;

public interface StudentRepository
        extends CrudRepository<Student, Long> {
}