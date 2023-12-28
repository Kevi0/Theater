package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;

    @PostMapping(value = "/updatePassword")
    public ResponseEntity<String> updatePassword(
            String username,
            String newPassword
    ) {
        return userService.changePassword(username, newPassword)
                .map(user -> ResponseEntity.ok("Password updated"))
                .orElse(ResponseEntity.badRequest().body("User not found"));
    }

}
