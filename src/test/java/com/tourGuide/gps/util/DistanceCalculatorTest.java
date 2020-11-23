package com.tourGuide.gps.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourGuide.gps.domain.Location;
import com.tourGuide.gps.util.DistanceCalculator;

@SpringBootTest
public class DistanceCalculatorTest {

    @Autowired
    private DistanceCalculator distanceCalculator;

    private Location tourEiffelLocation;
    private Location disneylandParisLocation;

    @BeforeEach
    public void setUpPerTest() {
        tourEiffelLocation = new Location(48.858331, 2.294481);
        disneylandParisLocation = new Location(48.872448, 2.776794);
    }

    @Test
    @Tag("Miles")
    @DisplayName("Get distance in Miles - OK")
    public void givenTwoValidLocation_whenGetDistance_thenReturnCorrectDouble() {
        // GIVEN

        // WHEN
        double result = distanceCalculator.getDistanceInMiles(
                tourEiffelLocation, disneylandParisLocation);

        // THEN
        assertThat(result).isEqualTo(21.928803618826255d);
    }

}
