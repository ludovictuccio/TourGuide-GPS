package com.tourGuide.gps.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.util.EntityToDtoConversion;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;

@SpringBootTest
public class GpsServiceTest {

    @Autowired
    public IGpsService gpsService;

    @MockBean
    private GpsUtil gpsUtil;

    @MockBean
    private EntityToDtoConversion entityToDtoConversion;

    private List<Attraction> attractionsList;

    private Attraction tourEiffel;
    private Attraction louvre;
    private Attraction lesInvalides;
    private Attraction lePantheon;
    private Attraction disneylandParis;
    private Attraction futuroscope;
    private Attraction vieuxPortDeMarseille;
    private Attraction basiliqueNotreDameDeFourviere;

    @BeforeEach
    public void setUpPerTest() {
        tourEiffel = new Attraction("Tour Eiffel", "Paris", "France",
                48.858331d, 2.294481d);
        louvre = new Attraction("Musée du Louvre", "Paris", "France", 48.861147,
                2.338028);
        lesInvalides = new Attraction("Hôtel des Invalides", "Paris", "France",
                48.853241, 2.312107);
        lePantheon = new Attraction("Le Panthéon", "Paris", "France", 48.846012,
                2.345924);
        disneylandParis = new Attraction("Disneyland Paris", "Paris", "France",
                48.872448, 2.776794);
        futuroscope = new Attraction("Futuroscope", "Poitiers", "France",
                46.667134, 0.367085);
        vieuxPortDeMarseille = new Attraction("Vieux-Port de Marseille",
                "Marseille", "France", 43.295364, 5.37439);
        basiliqueNotreDameDeFourviere = new Attraction(
                "Basilique Notre-Dame de Fourvière", "Lyon", "France",
                45.761347, 4.821883);

        attractionsList = new ArrayList<>();
        attractionsList.add(tourEiffel);
        attractionsList.add(louvre);
        attractionsList.add(lesInvalides);
        attractionsList.add(lePantheon);
        attractionsList.add(disneylandParis);
        attractionsList.add(futuroscope);
        attractionsList.add(vieuxPortDeMarseille);
        attractionsList.add(basiliqueNotreDameDeFourviere);
    }

    @Test
    @Tag("getAllAttractions")
    @DisplayName("Get All Attractions - OK")
    public void givenAttractions_whenGetAllAttractions_thenReturnCorrectListSize() {
        // GIVEN
        when(gpsUtil.getAttractions()).thenReturn(attractionsList);

        // WHEN
        List<AttractionDto> result = gpsService.getAllAttractions();

        // THEN
        assertThat(result.size()).isEqualTo(8);

    }
}
