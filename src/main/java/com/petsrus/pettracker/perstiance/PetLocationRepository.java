package com.petsrus.pettracker.perstiance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PetLocationRepository extends JpaRepository<PetLocation, Long> {

     Optional<PetLocation> findFirstByOwnerIdAndPetIdOrderByIdDesc(long ownerId, long petId);

     List<PetLocation> findByOwnerIdAndPetIdAndWhenGreaterThanOrderByIdDesc(long ownerId, long petId, Timestamp when, Pageable pageable);
}
