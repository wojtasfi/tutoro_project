package com.tutoro.rest;

import com.tutoro.dto.UserDto;
import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import com.tutoro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserAdministration {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private ResponseEntity<String> register(@RequestBody UserDto userDto) {
        if (!tutorService.checkIfTutorExists(userDto.getUsername())) {
            Tutor tutor = Tutor.builder()
                    .username(userDto.getUsername())
                    .email(userDto.getEmail())
                    .build();

            tutorService.saveTutor(tutor);
            userService.saveNewUser(userDto);
            return ResponseEntity.created(URI.create("/tutors/profile/" + tutor.getUsername())).build();
        } else {

            return ResponseEntity.badRequest().body("Duplicated tutor");
        }

    }

    @GetMapping("validate/username")
    private Boolean checkUsername(@RequestParam String username) {
        if (tutorService.checkIfTutorExists(username)) {
            return false;
        }
        return true;
    }

    @GetMapping("validate/email")
    private ResponseEntity checkEmail(@RequestParam String email) {
        if (tutorService.checkIfEmailExists(email)) {
            return ResponseEntity.badRequest().body("Duplicated");
        }
        return ResponseEntity.ok("Unique");
    }

    @GetMapping("verify/email/{token}")
    private ResponseEntity<String> verifiyRegistrationToken(@PathVariable String tokenString) {
        UUID token = UUID.fromString(tokenString);

        if (userService.verifyToken(token)) {
            return ResponseEntity.ok("Verified");
        }
        return ResponseEntity.badRequest().body("Token not valid");
    }
}
