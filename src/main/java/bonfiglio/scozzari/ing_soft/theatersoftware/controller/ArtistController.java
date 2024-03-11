package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.ArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist.ArtistMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.PermanentWorkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    private ArtistServiceImpl artistService;
    private UnemployedServiceImpl unemployedService;
    private TemporaryWorkServiceImpl temporaryWorkService;
    private PermanentWorkService permanentWorkService;
    private StudentServiceImpl studentService;
    private RetiredServiceImpl retiredService;
    private BankAccountITServiceImpl bankAccountITService;
    private BankAccountESServiceImpl bankAccountESService;

    private final ArtistMapper artistMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO artistDTO
    ) throws Exception {
        if (artistDTO instanceof ArtistDTO dto){

            Work work = null;
            BankAccount bankAccount = null;

            if (dto.getSubWorkDTO() instanceof SubPermanentWorkDTO)
                work = permanentWorkService.addPermanentWork(artistMapper.permanentWorkDTOToPermanentWork(artistDTO));
            if (dto.getSubWorkDTO() instanceof SubTemporaryWorkDTO)
                work = temporaryWorkService.addTemporaryWork(artistMapper.temporaryWorkDTOToTemporaryWork(artistDTO));
            if (dto.getSubWorkDTO() instanceof SubRetiredDTO)
                work = retiredService.addRetired(artistMapper.retiredDTOToRetired(artistDTO));
            if (dto.getSubWorkDTO() instanceof SubUnemployedDTO)
                work = unemployedService.addUnemployed(artistMapper.unemployedDTOToUnemployed(artistDTO));
            if (dto.getSubWorkDTO() instanceof SubStudentDTO)
                work = studentService.addStudent(artistMapper.studentDTOToStudent(artistDTO));

            if (dto.getSubBankAccountDTO() instanceof SubBankAccountITDTO)
                bankAccount = bankAccountITService.addBankAccountIT(artistMapper.bankAccountITDTOToBankAccountIT(artistDTO));
            if (dto.getSubBankAccountDTO() instanceof SubBankAccountESDTO)
                bankAccount = bankAccountESService.addBankAccountES(artistMapper.bankAccountESDTOToBankAccountES(artistDTO));

            artistService.addArtist(artistMapper.artistDTOToArtist(artistDTO),
                    dto.getSubArtistDTO().getIdUser(),
                    dto.getSubArtistDTO().getIdTypologies(), work, bankAccount);

        }
        return new ResponseEntity<>(new ResponseMessage("Artist added"), HttpStatus.OK);
    }

}
