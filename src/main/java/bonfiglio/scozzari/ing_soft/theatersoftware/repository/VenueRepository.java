package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    @NotNull Optional<Venue> findById(@NotNull Long id);

    Optional<Venue> findVenueByName(String name);
}
