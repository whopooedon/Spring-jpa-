package com.project05.service;

import com.project05.repository.BikeRepository;
import com.project05.domain.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<String> getAllDistricts() {
        return bikeRepository.findAllDistricts();
    }

    public List<String> getNeighborhoodsByDistrict(String district) {
        return bikeRepository.findNeighborhoodsByDistrict(district);
    }

    public List<Bike> findBikeLocations(String district, String neighborhood) {
        return bikeRepository.findBikeLocationsByDistrictAndNeighborhood(district, neighborhood);
    }

    // 여기에 필요에 따라 추가적인 CRUD 작업 메소드를 구현할 수 있습니다.
}

