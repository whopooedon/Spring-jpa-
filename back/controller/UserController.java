package com.project05.controller;

import com.project05.domain.User;
import com.project05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerAction")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok().body(Map.of("success", savedUser != null));
    }

    @PostMapping("/loginAction")
    public ResponseEntity<?> loginAction(@RequestBody Map<String, String> credentials, HttpSession session) {
        String userid = credentials.get("userid");
        String userpw = credentials.get("userpw");
        
        // Implement your own login logic in the UserService
        boolean isValidUser = userService.validateUserLogin(userid, userpw);
        if (isValidUser) {
            session.setAttribute("LOGIN_USER_ID", userid);
            return ResponseEntity.ok().body(Map.of("success", true));
        } else {
            return ResponseEntity.badRequest().body(Map.of("success", false));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok().body(Map.of("success", true, "message", "Logged out successfully."));
    }

    @GetMapping("/user/details/{userid}")
    public ResponseEntity<?> getUserDetails(@PathVariable("userid") String userid) {
        Optional<User> user = userService.findUserById(userid);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        boolean isUpdated = userService.updateUser(user);
        if (isUpdated) {
            return ResponseEntity.ok().body(Map.of("message", "User updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Update failed"));
        }
    }

    @DeleteMapping("/user/delete/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable String userid) {
        boolean isDeleted = userService.deleteUser(userid);
        if (isDeleted) {
            return ResponseEntity.ok().body(Map.of("message", "User deleted successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Deletion failed"));
        }
    }

    // Additional endpoints can be added here as needed
}
