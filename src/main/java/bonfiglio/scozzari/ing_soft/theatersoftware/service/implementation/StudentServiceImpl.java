package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Student;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.StudentRepository;
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
