package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.responses.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;

    @PostMapping(value = "/changePassword")
    public ResponseEntity<ResponseMessage> changePassword(
            @RequestBody String username,
            @RequestBody String newPassword
    ) throws Exception {
        userService.changePassword(username, newPassword);

        return ResponseEntity.ok(new ResponseMessage("Password changed successfully!"));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseMessage> delete(
            @RequestBody String username
    ) throws Exception {
        userService.deleteUser(username);

        return ResponseEntity.ok(new ResponseMessage("User deleted successfully!"));
    }

}
