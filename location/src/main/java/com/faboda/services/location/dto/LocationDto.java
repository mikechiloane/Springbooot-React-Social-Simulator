package com.faboda.services.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LocationDto {
    public  String username;
    public  double lng;
    public  double lat;


}
