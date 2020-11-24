package com.tourGuide.gps.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.domain.dto.VisitedLocationDto;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@SpringBootTest
public class EntityToDtoConversionTest {

    @Autowired
    private EntityToDtoConversion entityToDtoConversion;

    private Attraction attraction;

    private VisitedLocation visitedLocation;

    @BeforeEach
    public void setUpPerTest() {
        attraction = new Attraction("Tour Eiffel", "Paris", "France",
                48.858331d, 2.294481d);
        visitedLocation = new VisitedLocation(UUID.randomUUID(),
                new Location(48.858331d, 2.294481d), new Date());
    }

    @Test
    @Tag("VisitedLocationDto")
    @DisplayName("VisitedLocationDto - OK")
    public void givenVisitedLocation_whenConvertToDto_thenReturnOk() {
        // GIVEN

        // WHEN
        VisitedLocationDto result = entityToDtoConversion
                .convertVisitedLocationToDto(visitedLocation);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(visitedLocation.userId);
        assertThat(result.getLatitude())
                .isEqualTo(visitedLocation.location.latitude);
        assertThat(result.getLongitude())
                .isEqualTo(visitedLocation.location.longitude);
        assertThat(result.getTimeVisited())
                .isEqualTo(visitedLocation.timeVisited);
    }

    @Test
    @Tag("AttractionDto")
    @DisplayName("AttractionDto - OK")
    public void givenAttraction_whenConvertToDto_thenReturnOk() {
        // GIVEN

        // WHEN
        AttractionDto result = entityToDtoConversion
                .convertAttractionToDto(attraction);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getAttractionName()).isEqualTo("Tour Eiffel");
        assertThat(result.getLocation().getLatitude()).isEqualTo(48.858331d);
        assertThat(result.getLocation().getLongitude()).isEqualTo(2.294481d);
        assertThat(result.getCity()).isEqualTo("Paris");
        assertThat(result.getState()).isEqualTo("France");
        assertThat(result.getAttractionId()).isEqualTo(attraction.attractionId);
    }

}
