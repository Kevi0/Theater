package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UpdatePasswordRequestDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UpdateRequestDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserSummaryDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user.UpdatePasswordRequestMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user.UpdateRequestMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("deprecation")
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;

    private final UpdateRequestMapper updateRequestMapper;
    private final UpdatePasswordRequestMapper updatePasswordRequestMapper;

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO requestDTO
    ) throws UserNotFoundException, IllegalAccessException, InvalidDataException {

        try {

            if (requestDTO instanceof UpdateRequestDTO) {
                userService.updateUser(id, updateRequestMapper.userDTOToUser(requestDTO));

                return new ResponseEntity<>(new ResponseMessage("User updated"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("User not updated - Invalid CustomType");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Message Not Readable");
        }
    }

    @RequestMapping(value = "/update-password/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updatePassword(
            @PathVariable Long id,
            @RequestBody InputDTO requestDTO
    ) throws InvalidDataException, UserNotFoundException {

        if (requestDTO instanceof UpdatePasswordRequestDTO) {
            userService.updatePassword(id, updatePasswordRequestMapper.userDTOToUser(requestDTO));

            return new ResponseEntity<>(new ResponseMessage("Password updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Password not updated");
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws UserNotFoundException {

        if (userService.deleteUser(id).isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("User deleted"), HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserSummaryDTO>> getAllUsers() throws DataAccessServiceException {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{username}", method = RequestMethod.GET)
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserIdByUsername(username), HttpStatus.OK);
    }

}
