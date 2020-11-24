package com.tourGuide.gps.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.domain.dto.VisitedLocationDto;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@Component
public class EntityToDtoConversion {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method used to convert Attraction entity to Attraction Dto.
     *
     * @param attraction
     * @return AttractionDto attractionDto
     */
    public AttractionDto convertAttractionToDto(final Attraction attraction) {
        AttractionDto attractionDto = modelMapper.map(attraction,
                AttractionDto.class);
        attractionDto.setAttractionName(attraction.attractionName);
        attractionDto.setLocation(attraction.latitude, attraction.longitude);
        attractionDto.setCity(attraction.city);
        attractionDto.setState(attraction.state);
        attractionDto.setAttractionId(attraction.attractionId);
        return attractionDto;
    }

    /**
     * Method used to convert VisitedLocation entity to Dto.
     *
     * @param visitedLocation
     * @return visitedLocationDto
     */
    public VisitedLocationDto convertVisitedLocationToDto(
            final VisitedLocation visitedLocation) {
        VisitedLocationDto visitedLocationDto = modelMapper.map(visitedLocation,
                VisitedLocationDto.class);
        visitedLocationDto.setUserId(visitedLocation.userId);
        visitedLocationDto.setLatitude(visitedLocation.location.latitude);
        visitedLocationDto.setLongitude(visitedLocation.location.longitude);
        visitedLocationDto.setTimeVisited(visitedLocation.timeVisited);
        return visitedLocationDto;
    }
}
