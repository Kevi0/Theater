package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.agency.AgencyDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.ArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.opera.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.play.PlayDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.season.SeasonDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserAuthenticationDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserRegistrationDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.venue.VenueDTO;
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
        @JsonSubTypes.Type(value = VenueDTO.class, name = "registrationVenue"),
        @JsonSubTypes.Type(value = UserRegistrationDTO.class, name = "registrationUser"),
        @JsonSubTypes.Type(value = UserAuthenticationDTO.class, name = "authenticationUser")
})
public interface InputDTO {
}
