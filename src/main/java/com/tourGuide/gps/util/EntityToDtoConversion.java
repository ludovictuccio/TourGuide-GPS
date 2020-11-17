package com.tourGuide.gps.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tourGuide.gps.domain.dto.AttractionDto;

import gpsUtil.location.Attraction;

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
    public AttractionDto convertToDto(final Attraction attraction) {
        AttractionDto attractionDto = modelMapper.map(attraction,
                AttractionDto.class);
        attractionDto.setAttractionName(attraction.attractionName);
        attractionDto.setLocation(attraction.latitude, attraction.longitude);
        attractionDto.setCity(attraction.city);
        attractionDto.setState(attraction.state);
        attractionDto.setAttractionId(attraction.attractionId);
        return attractionDto;
    }
}
