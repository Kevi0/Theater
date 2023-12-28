package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationAgency.AgencyDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.ArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationOpera.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationPlay.PlayDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationSeason.SeasonDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationTheater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationVenue.VenueDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "CustomType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AgencyDTO.class, name = "registrationAgency"),
        @JsonSubTypes.Type(value = ArtistDTO.class, name = "registrationArtist"),
        @JsonSubTypes.Type(value = OperaDTO.class, name = "registrationOpera"),
        @JsonSubTypes.Type(value = PlayDTO.class, name = "registrationPlay"),
        @JsonSubTypes.Type(value = SeasonDTO.class, name = "registrationSeason"),
        @JsonSubTypes.Type(value = TheaterDTO.class, name = "registrationTheater"),
        @JsonSubTypes.Type(value = VenueDTO.class, name = "registrationVenue")
})
public interface InputDTO {
}
