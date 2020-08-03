package com.petsrus.pettracker.services;

import com.petsrus.pettracker.LocationDto;
import com.petsrus.pettracker.perstiance.Owner;
import com.petsrus.pettracker.perstiance.PetLocation;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PetTrackerService {
    PetLocation getLatestPetLocation(long ownerId, long petId) throws EntityNotFoundException;
    List<PetLocation> getPetLocationHistory(long ownerId, long petId, long when, int pageNum, int pageSize);
    Owner getOwner(long ownerId);
    void createLocation(LocationDto location);
}
