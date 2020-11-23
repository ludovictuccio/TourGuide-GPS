package com.tourGuide.gps.services;

import java.util.List;

import com.tourGuide.gps.domain.ClosestAttraction;
import com.tourGuide.gps.domain.dto.AttractionDto;

public interface IGpsService {

    List<AttractionDto> getAllAttractions();

    List<ClosestAttraction> getClosestAttractions(String userName);

}
