package com.tourGuide.gps.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourGuide.gps.domain.ClosestAttraction;
import com.tourGuide.gps.domain.Location;
import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.domain.dto.UserDto;
import com.tourGuide.gps.domain.dto.VisitedLocationDto;
import com.tourGuide.gps.proxies.MicroserviceRewardsProxy;
import com.tourGuide.gps.proxies.MicroserviceUserProxy;
import com.tourGuide.gps.util.DistanceCalculator;
import com.tourGuide.gps.util.EntityToDtoConverter;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;

@Service
public class GpsService implements IGpsService {

    @Autowired
    private GpsUtil gpsUtil;

    @Autowired
    private MicroserviceRewardsProxy microserviceRewardsProxy;

    @Autowired
    private MicroserviceUserProxy microserviceUserProxy;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    private EntityToDtoConverter entityToDtoConversion;

    /**
     * {@inheritDoc}
     */
    public List<AttractionDto> getAllAttractions() {

        List<AttractionDto> allAttractionsDto = new ArrayList<>();

        for (Attraction attraction : gpsUtil.getAttractions()) {
            AttractionDto attractionDto = entityToDtoConversion
                    .convertAttractionToDto(attraction);
            allAttractionsDto.add(attractionDto);
        }
        return allAttractionsDto;
    }

    /**
     * {@inheritDoc}
     */
    public List<ClosestAttraction> getClosestAttractions(
            final String userName) {

        UserDto userDto = microserviceUserProxy.getUserDto(userName);
        Location location = userDto.getLastLocation();

        List<AttractionDto> attractionsList = getAllAttractions();
        List<ClosestAttraction> theFiveClosestAttractions = new ArrayList<>();

        if (attractionsList.isEmpty()) {
            return theFiveClosestAttractions;
        }

        attractionsList.stream()
                .sorted((attraction1, attraction2) -> Double.compare(
                        distanceCalculator.getDistanceInMiles(location,
                                attraction1.getLocation()),
                        distanceCalculator.getDistanceInMiles(location,
                                attraction2.getLocation())))
                .limit(5).forEach(attraction -> {

                    int attractionRewardsPoints = microserviceRewardsProxy
                            .getAttractionRewards(attraction.getAttractionId(),
                                    userDto.getUserId());

                    ClosestAttraction closestAttraction = new ClosestAttraction(
                            attraction.getAttractionName(),
                            attraction.getLocation(), location,
                            distanceCalculator.getDistanceInMiles(
                                    attraction.getLocation(), location),
                            attractionRewardsPoints);

                    theFiveClosestAttractions.add(closestAttraction);
                });
        return theFiveClosestAttractions;
    }

    /**
     * {@inheritDoc}
     */
    public VisitedLocationDto getUserInstantLocation(final UUID userId) {
        return entityToDtoConversion
                .convertVisitedLocationToDto(gpsUtil.getUserLocation(userId));
    }

}
