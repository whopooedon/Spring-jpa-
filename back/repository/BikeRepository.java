package com.project05.repository;

import com.project05.domain.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, String> {

    @Query("SELECT DISTINCT b.addr1 FROM Address b")
    List<String> findAllDistricts();

    @Query("SELECT b.addr2 FROM Address b WHERE b.addr1 = :district")
    List<String> findNeighborhoodsByDistrict(@Param("district") String district);

    @Query("SELECT b FROM Bike b WHERE b.bikeAddr1 LIKE CONCAT('%', :district, '%') AND b.bikeAddr1 LIKE CONCAT('%', :neighborhood, '%')")
    List<Bike> findBikeLocationsByDistrictAndNeighborhood(@Param("district") String district, @Param("neighborhood") String neighborhood);
}
