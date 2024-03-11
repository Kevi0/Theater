package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Student;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;

import java.util.Optional;

public interface StudentService {

    Work addStudent(Student student);

    //TODO UPDATE

    Optional<Student> deleteStudent(Long id);

}
