package com.tourGuide.gps.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tourGuide.gps.domain.Location;
import com.tourGuide.gps.domain.dto.UserDto;
import com.tourGuide.gps.proxies.MicroserviceRewardsProxy;
import com.tourGuide.gps.proxies.MicroserviceUserProxy;
import com.tourGuide.gps.services.IGpsService;

import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GpsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    public IGpsService gpsService;

    @MockBean
    private GpsUtil gpsUtil;

    @MockBean
    private MicroserviceUserProxy microserviceUserProxy;

    @MockBean
    private MicroserviceRewardsProxy microserviceRewardsProxy;

    private static final String URI_GET_CLOSEST_ATTRACTIONS = "/gps/getClosestAttractions/internalUser1";
    private static final String URI_GET_TRACK_USER_LOCATION = "/gps/getUserInstantLocation/internalUser1";

    private static final String USER_TEST_1 = "internalUser1";

    @BeforeEach
    public void setUpPerTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Tag("trackUserLocation")
    @DisplayName("Track User Location - OK")
    public void givenUserBody_whenTrackLocation_thenReturnOk()
            throws Exception {

        UserDto userDto = new UserDto(UUID.randomUUID(),
                new Location(48.858331, 2.294481));
        when(microserviceUserProxy.getUserDto(USER_TEST_1)).thenReturn(userDto);

        gpsUtil.location.Location gpsLocation = new gpsUtil.location.Location(
                48.858331, 2.294481);

        VisitedLocation visitedLocation = new VisitedLocation(
                userDto.getUserId(), gpsLocation, new Date());

        when(gpsUtil.getUserLocation(userDto.getUserId()))
                .thenReturn(visitedLocation);

        this.mockMvc
                .perform(get(URI_GET_TRACK_USER_LOCATION)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Tag("GetClosestAttractions")
    @DisplayName("Get Closest Attractions - OK")
    public void givenExistingUser_whenGetClosestAttractions_thenReturnOk()
            throws Exception {
        UserDto userDto = new UserDto(UUID.randomUUID(),
                new Location(48.858331, 2.294481));
        when(microserviceUserProxy.getUserDto(USER_TEST_1)).thenReturn(userDto);

        this.mockMvc
                .perform(get(URI_GET_CLOSEST_ATTRACTIONS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
    }

}
