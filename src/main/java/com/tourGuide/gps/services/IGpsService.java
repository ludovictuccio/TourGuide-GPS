package com.tourGuide.gps.services;

import java.util.List;
import java.util.UUID;

import com.tourGuide.gps.domain.ClosestAttraction;
import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.domain.dto.VisitedLocationDto;

public interface IGpsService {

    /**
     * Method used to get all attractions Dto (to convert Attraction latitude /
     * longitude to Location).
     *
     * @return List<AttractionDto> allAttractionsDto
     */
    List<AttractionDto> getAllAttractions();

    /**
     * This method will return the five closest attractions since the last
     * user's location, and determine attraction rewards from RewardsCentral in
     * microservice rewards.
     * 
     * @param userName
     * @return 5 ClosestAttraction
     */
    List<ClosestAttraction> getClosestAttractions(final String userName);

    /**
     * Method used to track user location from GpsUtil.
     *
     * @param user UUID
     * @return visitedLocationDto
     */
    VisitedLocationDto getUserInstantLocation(final UUID userId);

}
