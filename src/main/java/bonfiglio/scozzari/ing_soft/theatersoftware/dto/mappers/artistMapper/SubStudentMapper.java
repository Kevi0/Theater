package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.artistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.SubStudentDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Student;
import org.springframework.stereotype.Component;

@Component
public class SubStudentMapper {

    public Student subStudentDTOToStudent(
            SubStudentDTO subStudentDTO
    ){
        if(subStudentDTO == null){
            return null;
        }

        Student student = new Student();

        student.setStudies(subStudentDTO.getStudies());

        return student;

    }

}
