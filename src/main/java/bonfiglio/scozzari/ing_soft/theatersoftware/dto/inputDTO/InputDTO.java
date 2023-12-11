package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "CustomType")
@JsonSubTypes({
        /*@JsonSubTypes.Type(value = RegistrationArtistDTO.class, name = "registrationArtist"),
        @JsonSubTypes.Type(value = TheaterDTO.class, name = "registrationTheater"),
        @JsonSubTypes.Type(value = AgencyDTO.class, name = "registrationAgency"),
        @JsonSubTypes.Type(value = SeasonDTO.class, name = "registrationSeason"),
        @JsonSubTypes.Type(value = OperaDTO.class, name = "registrationOpera"),
        @JsonSubTypes.Type(value = VenueDTO.class, name = "registrationVenue"),
        @JsonSubTypes.Type(value = PlayDTO.class, name = "registrationPlay"),
        @JsonSubTypes.Type(value = OperaArtistDTO.class, name = "registrationOperaArtist")*/
})
public interface InputDTO {
}
