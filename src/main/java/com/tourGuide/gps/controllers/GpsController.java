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

@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    public IGpsService gpsService;

    @GetMapping("/getClosestAttractions/{userName}")
    public List<ClosestAttraction> getClosestAttractions(
            @PathVariable("userName") String userName) {
        return gpsService.getClosestAttractions(userName);
    }

    @GetMapping("/getAllAttractions")
    public List<AttractionDto> getAllAttractions() {
        return gpsService.getAllAttractions();
    }

    @GetMapping("/getUserInstantLocation/{userId}")
    public VisitedLocationDto getUserInstantLocation(
            @PathVariable("userId") UUID userId) {
        return gpsService.getUserInstantLocation(userId);
    }

}
