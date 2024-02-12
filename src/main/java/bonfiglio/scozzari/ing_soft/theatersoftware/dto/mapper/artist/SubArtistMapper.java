package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Artist;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Gender;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Occupation;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.StateOfCitizenship;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Taxation;
import org.springframework.stereotype.Component;

@Component
public class SubArtistMapper {
    public Artist artistDTOToArtist(
            SubArtistDTO subArtistDTO
    ){
        if(subArtistDTO == null)
            return null;

        Artist artist = new Artist();

        artist.setGender(Gender.valueOf(subArtistDTO.getGender()));
        artist.setBirthDate(subArtistDTO.getBirthDate());
        artist.setBirthPlace(subArtistDTO.getBirthPlace());
        artist.setBirthState(subArtistDTO.getBirthState());
        artist.setEducationTitle(subArtistDTO.getEducationTitle());
        artist.setIsEuropean(subArtistDTO.getIsEuropean());
        artist.setStateOfCitizenship(StateOfCitizenship.valueOf(subArtistDTO.getStateOfCitizenship()));
        artist.setCellPhone1(subArtistDTO.getTel1());
        artist.setCellPhone2(subArtistDTO.getTel2());
        artist.setPec(subArtistDTO.getPec());
        artist.setWebsite(subArtistDTO.getWebsite());
        artist.setResidence(subArtistDTO.getResidence());
        artist.setPostalCodeOfResidence(subArtistDTO.getPostalCodeOfResidence());
        artist.setCityOfResidence(subArtistDTO.getCityOfResidence());
        artist.setProvinceOfResidence(subArtistDTO.getProvinceOfResidence());
        artist.setRegionOfResidence(subArtistDTO.getRegionOfResidence());
        artist.setStateOfResidence(subArtistDTO.getStateOfResidence());
        artist.setDomicile(subArtistDTO.getDomicile());
        artist.setPostalCodeOfDomicile(subArtistDTO.getPostalCodeOfDomicile());
        artist.setCityOfDomicile(subArtistDTO.getCityOfDomicile());
        artist.setProvinceOfDomicile(subArtistDTO.getProvinceOfDomicile());
        artist.setRegionOfDomicile(subArtistDTO.getRegionOfDomicile());
        artist.setStateOfDomicile(subArtistDTO.getStateOfDomicile());
        artist.setStageName(subArtistDTO.getStageName());
        artist.setInstrument(subArtistDTO.getInstrument());
        artist.setVoice(subArtistDTO.getVoice());
        artist.setIsIva(subArtistDTO.getIsIva());
        artist.setIva(subArtistDTO.getIva());
        artist.setTaxation(Taxation.valueOf(subArtistDTO.getTaxation()));
        artist.setOccupation(Occupation.valueOf(subArtistDTO.getOccupation()));
        artist.setInpsNumber(subArtistDTO.getInpsNumber());
        artist.setMemberFrom(subArtistDTO.getMemberFrom());

        return artist;
    }

}
