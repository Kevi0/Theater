package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentById(Student student);

}
