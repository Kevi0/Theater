package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.subArtistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubStudentDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Student;
import org.springframework.stereotype.Component;

@Component
public class SubStudentMapper {
    public static Student subStudentDTOToStudent (SubStudentDTO subStudentDTO) {
        if (subStudentDTO == null) {
            return null;
        }

        Student student = new Student();
        student.setStudies(subStudentDTO.getStudies());

        return student;
    }
}
