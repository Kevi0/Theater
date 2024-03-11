package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;

import java.util.Optional;
import java.util.Set;

public interface TheaterService {

    void addTheater(Theater theater, Set<Long> idUsers) throws InvalidDataException, TheaterAlreadyExistException, UserNotFoundException;

    void updateTheater(Long id, Theater theater) throws InvalidDataException, IllegalAccessException, TheaterNotFoundException;

    Optional<Theater> deleteTheater(Long id) throws TheaterAlreadyExistException, TheaterNotFoundException, TheaterAlreadyDeletedException;

    Set<Optional<Theater>> getAllTheaters();

    Long getTheaterIdByName(String name) throws TheaterNotFoundException;

}
