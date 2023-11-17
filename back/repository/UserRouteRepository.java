package com.project05.repository;

import com.project05.domain.UserRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRouteRepository extends JpaRepository<UserRoute, Integer> { // Assuming routeSeq is the primary key

    // Standard CRUD operations are automatically provided by JpaRepository.

    // Custom query method to find routes by userid.
    List<UserRoute> findByUserid(String userid);

    // For adding and updating routes, use the save(UserRoute route) method.
    // For deleting a route, use delete(UserRoute route) method or deleteById(Integer id) if you have the routeSeq.
}
