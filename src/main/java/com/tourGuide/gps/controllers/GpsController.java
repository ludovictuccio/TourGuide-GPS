package com.tourGuide.gps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourGuide.gps.domain.ClosestAttraction;
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

    @GetMapping("/getUserInstantLocation/{userName}")
    public VisitedLocationDto getUserInstantLocation(
            @PathVariable("userName") String userName) {
        return gpsService.getUserInstantLocation(userName);
    }

}
