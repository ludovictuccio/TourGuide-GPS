package com.tourGuide.gps.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourGuide.gps.domain.ClosestAttraction;
import com.tourGuide.gps.domain.Location;
import com.tourGuide.gps.domain.dto.AttractionDto;
import com.tourGuide.gps.domain.dto.UserDto;
import com.tourGuide.gps.proxies.MicroserviceRewardsProxy;
import com.tourGuide.gps.proxies.MicroserviceUserProxy;
import com.tourGuide.gps.util.DistanceCalculator;
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

    @MockBean
    private MicroserviceRewardsProxy microserviceRewardsProxy;

    @MockBean
    private MicroserviceUserProxy microserviceUserProxy;

    @MockBean
    private DistanceCalculator distanceCalculator;

    private UserDto userDto;

    private List<Attraction> attractionsList;
    private Attraction tourEiffel;
    private Attraction louvre;
    private Attraction lesInvalides;
    private Attraction lePantheon;
    private Attraction disneylandParis;
    private Attraction futuroscope;
    private Attraction vieuxPortDeMarseille;
    private Attraction basiliqueNotreDameDeFourviere;

    private List<AttractionDto> attractionsListDto;
    private AttractionDto tourEiffelDto;
    private AttractionDto louvreDto;
    private AttractionDto lesInvalidesDto;
    private AttractionDto lePantheonDto;
    private AttractionDto disneylandParisDto;
    private AttractionDto futuroscopeDto;
    private AttractionDto vieuxPortDeMarseilleDto;
    private AttractionDto basiliqueNotreDameDeFourviereDto;

    @BeforeEach
    public void setUpPerTest() {
        tourEiffelDto = new AttractionDto("Tour Eiffel",
                new Location(48.858331, 2.294481), "Paris", "France",
                UUID.randomUUID());
        louvreDto = new AttractionDto("Musée du Louvre",
                new Location(48.861147, 2.338028), "Paris", "France",
                UUID.randomUUID());
        lesInvalidesDto = new AttractionDto("Hôtel des Invalides",
                new Location(48.853241, 2.312107), "Paris", "France",
                UUID.randomUUID());
        lePantheonDto = new AttractionDto("Le Panthéon",
                new Location(48.846012, 2.345924), "Paris", "France",
                UUID.randomUUID());
        disneylandParisDto = new AttractionDto("Disneyland Paris",
                new Location(48.872448, 2.776794), "Marnes-la-Vallee", "France",
                UUID.randomUUID());
        futuroscopeDto = new AttractionDto("Futuroscope",
                new Location(46.667134, 0.367085), "Poitiers", "France",
                UUID.randomUUID());
        vieuxPortDeMarseilleDto = new AttractionDto("Vieux-Port de Marseille",
                new Location(43.295364, 5.37439), "Marseille", "France",
                UUID.randomUUID());
        basiliqueNotreDameDeFourviereDto = new AttractionDto(
                "Basilique Notre-Dame de Fourvière",
                new Location(45.761347, 4.821883), "Lyon", "France",
                UUID.randomUUID());

        attractionsListDto = new ArrayList<>();
        attractionsListDto.add(tourEiffelDto);
        attractionsListDto.add(louvreDto);
        attractionsListDto.add(lesInvalidesDto);
        attractionsListDto.add(lePantheonDto);
        attractionsListDto.add(disneylandParisDto);
        attractionsListDto.add(futuroscopeDto);
        attractionsListDto.add(vieuxPortDeMarseilleDto);
        attractionsListDto.add(basiliqueNotreDameDeFourviereDto);

        tourEiffel = new Attraction("Tour Eiffel", "Paris", "France", 48.858331,
                2.294481);
        louvre = new Attraction("Musée du Louvre", "Paris", "France", 48.861147,
                2.338028);
        lesInvalides = new Attraction("Hôtel des Invalides", "Paris", "France",
                48.853241, 2.312107);
        lePantheon = new Attraction("Le Panthéon", "Paris", "France", 48.846012,
                2.345924);
        disneylandParis = new Attraction("Disneyland Paris", "Marnes-la-Vallee",
                "France", 48.872448, 2.776794);
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
    @Tag("GetClosestAttractions")
    @DisplayName("Get Closest Attractions - Ok")
    public void givenAttractions_whenGetClosest_thenReturnFiveAttractions() {
        // GIVEN
        userDto = new UserDto(UUID.randomUUID(),
                new Location(48.858331, 2.294481));
        when(microserviceUserProxy.getUserDto("jon")).thenReturn(userDto);

        when(gpsUtil.getAttractions()).thenReturn(attractionsList);

        when(entityToDtoConversion.convertToDto(tourEiffel))
                .thenReturn(tourEiffelDto);
        when(entityToDtoConversion.convertToDto(louvre)).thenReturn(louvreDto);
        when(entityToDtoConversion.convertToDto(lesInvalides))
                .thenReturn(lesInvalidesDto);
        when(entityToDtoConversion.convertToDto(lePantheon))
                .thenReturn(lePantheonDto);
        when(entityToDtoConversion.convertToDto(disneylandParis))
                .thenReturn(disneylandParisDto);
        when(entityToDtoConversion.convertToDto(futuroscope))
                .thenReturn(futuroscopeDto);
        when(entityToDtoConversion.convertToDto(vieuxPortDeMarseille))
                .thenReturn(vieuxPortDeMarseilleDto);
        when(entityToDtoConversion.convertToDto(basiliqueNotreDameDeFourviere))
                .thenReturn(basiliqueNotreDameDeFourviereDto);

        when(microserviceRewardsProxy.getAttractionRewards(
                tourEiffelDto.getAttractionId(), userDto.getUserId()))
                        .thenReturn(100);
        when(microserviceRewardsProxy.getAttractionRewards(
                louvreDto.getAttractionId(), userDto.getUserId()))
                        .thenReturn(200);
        when(microserviceRewardsProxy.getAttractionRewards(
                lesInvalidesDto.getAttractionId(), userDto.getUserId()))
                        .thenReturn(300);
        when(microserviceRewardsProxy.getAttractionRewards(
                lePantheonDto.getAttractionId(), userDto.getUserId()))
                        .thenReturn(400);
        when(microserviceRewardsProxy.getAttractionRewards(
                disneylandParisDto.getAttractionId(), userDto.getUserId()))
                        .thenReturn(500);

        // WHEN
        List<ClosestAttraction> result = gpsService
                .getClosestAttractions("jon");

        // THEN
        assertThat(result.size()).isEqualTo(5);

        assertThat(result.get(0).getAttractionName()
                .contains(tourEiffelDto.getAttractionName())).isTrue();
        assertThat(result.get(1).getAttractionName()
                .contains(louvreDto.getAttractionName())).isTrue();
        assertThat(result.get(2).getAttractionName()
                .contains(lesInvalidesDto.getAttractionName())).isTrue();
        assertThat(result.get(3).getAttractionName()
                .contains(lePantheonDto.getAttractionName())).isTrue();
        assertThat(result.get(4).getAttractionName()
                .contains(disneylandParisDto.getAttractionName())).isTrue();

        assertThat(result.get(0).getAttractionRewardsPoints()).isEqualTo(100);
        assertThat(result.get(1).getAttractionRewardsPoints()).isEqualTo(200);
        assertThat(result.get(2).getAttractionRewardsPoints()).isEqualTo(300);
        assertThat(result.get(3).getAttractionRewardsPoints()).isEqualTo(400);
        assertThat(result.get(4).getAttractionRewardsPoints()).isEqualTo(500);
    }

    @Test
    @Tag("GetClosestAttractions")
    @DisplayName("Get Closest Attractions - OK - 0 attraction")
    public void givenZeroAttraction_whenGetClosest_thenReturnEmptyList() {
        // GIVEN
        userDto = new UserDto(UUID.randomUUID(),
                new Location(48.858331, 2.294481));
        when(microserviceUserProxy.getUserDto("jon")).thenReturn(userDto);

        attractionsList.clear();
        when(gpsUtil.getAttractions()).thenReturn(attractionsList);

        // WHEN
        List<ClosestAttraction> result = gpsService
                .getClosestAttractions("jon");

        // THEN
        assertThat(result.size()).isEqualTo(0);
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
