package com.petsrus.pettracker.services.impl;


import com.petsrus.pettracker.LocationDto;
import com.petsrus.pettracker.perstiance.*;
import com.petsrus.pettracker.services.PetTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static com.petsrus.pettracker.Constants.*;

@Service
public class PetTrackerServiceImpl implements PetTrackerService {
    @Autowired
    private PetLocationRepository locationRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PetRepository petRepository;

    /**
     * Gets the latest pet location for the owner and pet
     * @param ownerId the id of the owner
     * @param petId the id of the pet (should belong to the owner)
     * @return A single PetLocation
     * @throws EntityNotFoundException if no pets found for the owner and pet ids
     */
    @Override
    public PetLocation getLatestPetLocation(long ownerId, long petId) throws EntityNotFoundException{
        Optional<PetLocation> latestOp = locationRepository.findFirstByOwnerIdAndPetIdOrderByIdDesc(ownerId, petId);
        if(latestOp.isEmpty()) throw new EntityNotFoundException(String.format("No location data found for owner: %d, pet: %d", ownerId, petId));
        return latestOp.get();
    }

    public List<PetLocation> getPetLocationHistory(long ownerId, long petId, long when, int pageNum, int pageSize){
        Optional<Owner> ownerOp = ownerRepository.findById(ownerId);
        if(ownerOp.isEmpty()) throw new EntityNotFoundException(String.format("No owner data found for owner: %d", ownerId));

        //
        // The maximum number of days that a free-tier owner can look back is 1 day, premium owners can look back up to 30 days
        //
        boolean premium = ownerOp.get().isPremiumAccount();
        when = Math.min(when, (premium ? MAX_PREMIUM_DAYS : MAX_FREE_DAYS ) * DAY_IN_MILLIS);

        return locationRepository.findByOwnerIdAndPetIdAndWhenGreaterThanOrderByIdDesc(ownerId, petId, new Timestamp(new Date().getTime() - when), PageRequest.of(pageNum, pageSize));
    }

    public Owner getOwner(long ownerId){
        Optional<Owner> ownerOp = ownerRepository.findById(ownerId);
        if(ownerOp.isEmpty()) throw new EntityNotFoundException(String.format("No owner data found for owner: %d", ownerId));
        return ownerOp.get();
    }


    public void createLocation(LocationDto location){
        Optional<Pet> petOp = petRepository.findFirstByCollarId(location.getCollarId());
        if(petOp.isEmpty()) throw new EntityNotFoundException(String.format("No pet data found for collar: %s", location.getCollarId()));
        Pet pet = petOp.get();

        PetLocation petLocation = new PetLocation(
                pet.getOwnerId(),
                pet.getId(),
                location.getLongitude(),
                location.getLatitude(),
                location.getElevation(),
                location.getWhen(),
                pet.getZone());

        locationRepository.saveAndFlush(petLocation);
    }

}
