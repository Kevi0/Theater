package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.ArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces.SubBankAccountDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces.SubWorkDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArtistMapper {

    private SubArtistMapper subArtistMapper;
    private SubBankAccountITMapper subBankAccountITMapper;
    private SubBankAccountESMapper subBankAccountESMapper;
    private SubPermanentWorkMapper subPermanentWorkMapper;
    private SubTemporaryWorkMapper subTemporaryWorkMapper;
    private SubRetiredMapper subRetiredMapper;
    private SubUnemployedMapper subUnemployedMapper;
    private SubStudentMapper subStudentMapper;

    public Artist artistDTOToArtist (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto)
            return subArtistMapper.artistDTOToArtist(dto.getSubArtistDTO());
        else
            throw new ClassCastException();
    }

    public BankAccountIT bankAccountITDTOToBankAccountIT (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto){
            SubBankAccountDTO subBankAccountDTO = dto.getSubBankAccountDTO();
            if(subBankAccountDTO instanceof SubBankAccountITDTO){
                return subBankAccountITMapper.bankAccountITDTOToBankAccountIT((SubBankAccountITDTO) subBankAccountDTO);
            } else {
                throw new ClassCastException();
            }
        } else {
            throw new ClassCastException();
        }
    }

    public BankAccountES bankAccountESDTOToBankAccountES (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto){
            SubBankAccountDTO subBankAccountDTO = dto.getSubBankAccountDTO();
            if (subBankAccountDTO instanceof SubBankAccountESDTO){
                return subBankAccountESMapper.bankAccountESDTOToBankAccountES((SubBankAccountESDTO) subBankAccountDTO);
            } else {
                throw new ClassCastException();
            }
        } else {
            throw new ClassCastException();
        }
    }

    public PermanentWork permanentWorkDTOToPermanentWork (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto){
            SubWorkDTO subWorkDTO = dto.getSubWorkDTO();
            if (subWorkDTO instanceof SubPermanentWorkDTO){
                return subPermanentWorkMapper.subPermanentWorkDTOToPermanentWork((SubPermanentWorkDTO) subWorkDTO);
            } else {
                throw new ClassCastException();
            }
        } else {
            throw new ClassCastException();
        }
    }

    public TemporaryWork temporaryWorkDTOToTemporaryWork (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto){
            SubWorkDTO subWorkDTO = dto.getSubWorkDTO();
            if (subWorkDTO instanceof SubTemporaryWorkDTO){
                return subTemporaryWorkMapper.subTemporaryWorkDTOToTemporaryWork((SubTemporaryWorkDTO) subWorkDTO);
            } else {
                throw new ClassCastException();
            }
        } else {
            throw new ClassCastException();
        }
    }

    public Retired retiredDTOToRetired (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto){
            SubWorkDTO subWorkDTO = dto.getSubWorkDTO();
            if (subWorkDTO instanceof SubRetiredDTO){
                return subRetiredMapper.subRetiredDTOToRetired((SubRetiredDTO) subWorkDTO);
            } else {
                throw new ClassCastException();
            }
        } else {
            throw new ClassCastException();
        }
    }

    public Unemployed unemployedDTOToUnemployed (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto){
            SubWorkDTO subWorkDTO = dto.getSubWorkDTO();
            if (subWorkDTO instanceof SubUnemployedDTO){
                return subUnemployedMapper.subUnemployedDTOToUnemployed((SubUnemployedDTO) subWorkDTO);
            } else {
                throw new ClassCastException();
            }
        } else {
            throw new ClassCastException();
        }
    }

    public Student studentDTOToStudent (InputDTO artistDTO){
        if (artistDTO instanceof ArtistDTO dto){
            SubWorkDTO subWorkDTO = dto.getSubWorkDTO();
            if (subWorkDTO instanceof SubStudentDTO){
                return subStudentMapper.subStudentDTOToStudent((SubStudentDTO) subWorkDTO);
            } else {
                throw new ClassCastException();
            }
        } else {
            throw new ClassCastException();
        }
    }

}
