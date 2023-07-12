package com.faboda.services.location.service;

import com.faboda.services.location.dto.LocationDto;
import com.faboda.services.location.models.Location;
import com.faboda.services.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private static Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Async
    public CompletableFuture<List<Location>> getOtherUserLocation(String username) {
        logger.info("returning other location not " + username);
        return CompletableFuture.completedFuture(locationRepository.findByUsernameNot(username));
    }

    @Async
    public CompletableFuture<Location> getLocation(String username) {
        Location location = locationRepository.findLocationByUsername(username);
        logger.info("getLocation by username");

        return CompletableFuture.completedFuture(location);
    }


    @Async
    public void addNewUserToMap(String username, String name) {

        Location location = new Location();

        location.setUsername(username);
        location.setName(name);
        location.setLng(30.780125);
        location.setLat(-24.6223719);
        locationRepository.save(location);


    }

    @Async
    public CompletableFuture<Location> updateLocation(LocationDto locationDto) {
        logger.info("Location update");
        Location location = locationRepository.findLocationByUsername(locationDto.getUsername());
        if (location == null) {
            logger.info("username doesn't exist for:" + locationDto.getUsername());
            return CompletableFuture.completedFuture(null);
        }
        if (locationDto.lat == 0 || locationDto.lng == 0) {
            logger.info("Latitude and Longitude of zero value is not allowed.");
            return CompletableFuture.completedFuture(null);
        }

        location.setLat(locationDto.getLat());
        location.setLng(locationDto.getLng());
        locationRepository.save(location);
        return CompletableFuture.completedFuture(location);
    }

    public CompletableFuture<List<Location>> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        logger.info("Returning all locations");
        return CompletableFuture.completedFuture(locations);
    }


}
