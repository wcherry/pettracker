package com.petsrus.pettracker.perstiance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findFirstByCollarId(String collarId);
}
