package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Student;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;

import java.util.Optional;

public interface StudentService {

    Work addStudent(Student student);

    //TODO UPDATE

    Optional<Student> deleteStudent(Long id);

}
