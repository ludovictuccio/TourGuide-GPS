package com.tourGuide.gps.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourGuide.gps.domain.dto.AttractionDto;

import gpsUtil.location.Attraction;

@SpringBootTest
public class EntityToDtoConversionTest {

    @Autowired
    private EntityToDtoConversion entityToDtoConversion;

    private Attraction attraction;

    @BeforeEach
    public void setUpPerTest() {
        attraction = new Attraction("Tour Eiffel", "Paris", "France",
                48.858331d, 2.294481d);
    }

    @Test
    @Tag("AttractionDto")
    @DisplayName("AttractionDto - OK")
    public void givenAttraction_whenConvertToDto_thenReturnOk() {
        // GIVEN

        // WHEN
        AttractionDto result = entityToDtoConversion.convertToDto(attraction);

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
