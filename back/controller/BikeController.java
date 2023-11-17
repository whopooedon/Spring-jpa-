package com.project05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project05.domain.Bike;
import com.project05.service.BikeService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("/districts")
    public ResponseEntity<?> getDistricts() {
        List<String> districts = bikeService.getAllDistricts();
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/districts/{district}/neighborhoods")
    public ResponseEntity<?> getNeighborhoods(@PathVariable String district) {
        List<String> neighborhoods = bikeService.getNeighborhoodsByDistrict(district);
        return ResponseEntity.ok(neighborhoods);
    }

    @GetMapping("/bike-locations")
    public ResponseEntity<?> getBikeLocations(@RequestParam String district, @RequestParam String neighborhood) {
        List<Bike> bikes = bikeService.findBikeLocations(district, neighborhood);
        if (!bikes.isEmpty()) {
            return ResponseEntity.ok(bikes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
