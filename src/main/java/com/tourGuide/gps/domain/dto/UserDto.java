package com.tourGuide.gps.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tourGuide.gps.domain.Location;
import com.tourGuide.gps.domain.VisitedLocation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UserDto {

    @Getter
    @Setter
    private UUID userId;

    @Getter
    private Location lastLocation;

    private List<VisitedLocation> visitedLocations = new ArrayList<>();

    public void addToVisitedLocations(VisitedLocation visitedLocation) {
        visitedLocations.add(visitedLocation);
    }

    public List<VisitedLocation> getVisitedLocations() {
        return visitedLocations;
    }

    public void clearVisitedLocations() {
        visitedLocations.clear();
    }

    public UserDto(UUID userId, Location location) {
        super();
        this.userId = userId;
        this.lastLocation = location;
    }

}
