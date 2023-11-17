package com.project05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project05.domain.Culture;
import com.project05.service.CultureService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class CultureController {

    @Autowired
    private CultureService cultureService;
    
    @GetMapping("/culture-locations")
    public ResponseEntity<?> getCultureByAddress(@RequestParam String district) {
        List<Culture> cultures = cultureService.findCultureByAddress(district);
        if (!cultures.isEmpty()) {
            return ResponseEntity.ok(cultures);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Other CRUD operations for Bike can be added here as per requirement.
}

