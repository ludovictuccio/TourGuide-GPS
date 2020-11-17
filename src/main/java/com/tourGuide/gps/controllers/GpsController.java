package com.tourGuide.gps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.services.IGpsService;

@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    public IGpsService gpsService;

    @GetMapping("/getAllAttractions")
    public List<AttractionDto> getAllAttractions() {
        return gpsService.getAllAttractions();
    }

}
