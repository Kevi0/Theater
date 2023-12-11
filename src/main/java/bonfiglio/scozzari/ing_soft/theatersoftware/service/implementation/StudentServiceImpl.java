package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Student;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.PermanentWorkRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.StudentRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.StudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Work addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public Optional<Student> deleteStudent(Long id) {
        return Optional.empty();
    }
}
