package com.petsrus.pettracker.controllers;

import com.petsrus.pettracker.Constants;
import com.petsrus.pettracker.LocationDto;
import com.petsrus.pettracker.StatusDto;
import com.petsrus.pettracker.perstiance.Owner;
import com.petsrus.pettracker.perstiance.PetLocation;
import com.petsrus.pettracker.services.PetTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping(path="/api")
public class PetLocationController {
    @Autowired
    private PetTrackerService petTrackerService;

    @GetMapping(path="/owner/{ownerId}/pet/{petId}/location")
    public @ResponseBody
    PetLocation getPetLocation(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId) {
        return petTrackerService.getLatestPetLocation(ownerId, petId);
    }


    @GetMapping(path="/owner/{ownerId}/pet/{petId}/location/history")
    public @ResponseBody
    List<PetLocation> getPetLocationHistory(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId) {
        return petTrackerService.getPetLocationHistory(ownerId, petId, 60 * Constants.DAY_IN_MILLIS, 0, 2000);
    }


    @PostMapping(path="/location")
    public @ResponseBody
    StatusDto createLocation(@RequestBody LocationDto location){
        try{
            petTrackerService.createLocation(location);
            return new StatusDto("success");
        } catch(Exception ex){
            //TODO: Log exception
            return new StatusDto("failed", ex.getMessage());
        }
    }


    //TODO: Move to seperate owner controller.
    @GetMapping(path="/owner/{ownerId}")
    public @ResponseBody
    Owner getOwner(@PathVariable("ownerId") Long ownerId) {
        return petTrackerService.getOwner(ownerId);
    }


    @ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Pet Location not found")  // 404
    @ExceptionHandler(EntityNotFoundException.class)
    public void notFound(Exception ex) {
        //TODO:  Log the exception
    }



}
