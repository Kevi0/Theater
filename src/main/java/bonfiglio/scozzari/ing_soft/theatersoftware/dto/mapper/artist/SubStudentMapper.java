package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubStudentDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Student;
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
