package com.tourGuide.gps.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.util.EntityToDtoConversion;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;

@Service
public class GpsService implements IGpsService {

    @Autowired
    private GpsUtil gpsUtil;

    @Autowired
    private EntityToDtoConversion entityToDtoConversion;

    /**
     * Method used to get all attractions Dto (to convert Attraction latitude /
     * longitude to Location).
     *
     * @return List<AttractionDto> allAttractionsDto
     */
    public List<AttractionDto> getAllAttractions() {

        List<AttractionDto> allAttractionsDto = new ArrayList<>();
        List<Attraction> allAttractionsEntities = gpsUtil.getAttractions();

        for (Attraction attraction : allAttractionsEntities) {
            AttractionDto attractionDto = entityToDtoConversion
                    .convertToDto(attraction);
            allAttractionsDto.add(attractionDto);
        }

        return allAttractionsDto;
    }

}
