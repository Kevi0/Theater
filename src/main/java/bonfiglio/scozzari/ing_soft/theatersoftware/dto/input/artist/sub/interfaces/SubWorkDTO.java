package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "WorkType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SubUnemployedDTO.class, name = "Unemployed"),
        @JsonSubTypes.Type(value = SubTemporaryWorkDTO.class, name = "TemporaryWork"),
        @JsonSubTypes.Type(value = SubPermanentWorkDTO.class, name = "PermanentWork"),
        @JsonSubTypes.Type(value = SubStudentDTO.class, name = "Student"),
        @JsonSubTypes.Type(value = SubRetiredDTO.class, name = "Retired")
})
public interface SubWorkDTO {
}
