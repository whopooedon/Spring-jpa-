package com.project05.service;

import com.project05.domain.UserRoute;
import com.project05.repository.UserRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRouteService {

    @Autowired
    private UserRouteRepository userRouteRepository;

    // Method to add a user route and return a boolean indicating success
    public boolean addRoute(UserRoute route) {
        UserRoute savedRoute = userRouteRepository.save(route);
        return savedRoute != null; // Returns true if the route is successfully saved
    }
    
    // Method to retrieve all routes for a specific user
    public List<UserRoute> getUserRoutes(String userid) {
        return userRouteRepository.findByUserid(userid);
    }
    
    // Method to delete a user route
    public boolean deleteUserRouteBySeq(int routeSeq) {
        if (!userRouteRepository.existsById(routeSeq)) {
            return false;
        }
        userRouteRepository.deleteById(routeSeq);
        return true;
    }

    // Additional methods as per your business logic
}
