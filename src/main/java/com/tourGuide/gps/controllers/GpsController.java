package com.tourGuide.gps.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourGuide.gps.domain.ClosestAttraction;
import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.domain.dto.VisitedLocationDto;
import com.tourGuide.gps.services.IGpsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    public IGpsService gpsService;

    @ApiOperation(value = "GET Closest Attractions", notes = "Need pathvariable userName - Return 200 OK - This method will return the five closest attractions since the last user's location, and determine attraction rewards from RewardsCentral in microservice rewards.", response = ClosestAttraction.class)
    @GetMapping("/getClosestAttractions/{userName}")
    public List<ClosestAttraction> getClosestAttractions(
            @PathVariable("userName") String userName) {
        return gpsService.getClosestAttractions(userName);
    }

    @ApiOperation(value = "GET All Attractions", notes = "Return 200 OK - Method used to get all attractions Dto (to convert Attraction latitude/longitude to Location).", response = AttractionDto.class)
    @GetMapping("/getAllAttractions")
    public List<AttractionDto> getAllAttractions() {
        return gpsService.getAllAttractions();
    }

    @ApiOperation(value = "GET User instant location", notes = "Return 200 OK - Method used to track user location from GpsUtil.", response = VisitedLocationDto.class)
    @GetMapping("/getUserInstantLocation/{userId}")
    public VisitedLocationDto getUserInstantLocation(
            @PathVariable("userId") UUID userId) {
        return gpsService.getUserInstantLocation(userId);
    }

}
