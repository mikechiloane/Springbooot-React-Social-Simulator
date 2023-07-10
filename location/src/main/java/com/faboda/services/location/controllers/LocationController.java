package com.faboda.services.location.controllers;


import com.faboda.services.location.dto.LocationDto;
import com.faboda.services.location.models.Location;
import com.faboda.services.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/location")
public class LocationController {

    private final LocationService locationService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/forUser/{username}")
    public Location sendLocationForUser(@PathVariable("username") String username){
        return locationService.getLocation(username).join();
    }

    @GetMapping
    public List<Location> getAllLocations(){
        return locationService.getAllLocations().join();
    }

    @MessageMapping("/location")
    @SendTo("/topic/location")
    public Location send(@Payload Location location) {
        return location;
    }



    @GetMapping("/{username}")
    public List<Location> geOtherLocations (@PathVariable String username){
        return locationService.getOtherUserLocation(username).join();
    }


    @PostMapping
    public Location updateLocation(@RequestBody  LocationDto locationDto){
        simpMessagingTemplate.convertAndSend("/topic/location", locationService.getAllLocations().join());

         Location location = locationService.updateLocation(locationDto).join();
         simpMessagingTemplate.convertAndSend("/topic/location", locationService.getAllLocations().join());
         return location;
    }
    

}
