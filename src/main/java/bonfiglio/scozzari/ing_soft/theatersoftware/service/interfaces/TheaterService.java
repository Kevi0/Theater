package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;

import java.util.Optional;

public interface TheaterService {

    Optional<Theater> addTheater(Theater theater) throws Exception;

    //TODO UPDATE

    Optional<Theater> deleteTheater(Long id);

}
