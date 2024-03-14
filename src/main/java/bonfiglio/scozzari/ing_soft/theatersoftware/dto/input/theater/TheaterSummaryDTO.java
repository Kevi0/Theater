package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSummaryDTO {

    private String name;

    private String city;

    private String tel;

    private String email;

    private String website;

    public TheaterSummaryDTO(Theater theater) {
        this.name = theater.getName();
        this.city = theater.getCity();
        this.tel = theater.getTel();
        this.email = theater.getEmail();
        this.website = theater.getWebsite();
    }

}
