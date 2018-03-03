package com.tutoro.rest;

import com.tutoro.service.TutorService;
import com.tutoro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("validate/")
public class ValidationRest {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private UserService userService;

    @GetMapping("/username")
    private Boolean checkUsername(@RequestParam String username) {
        if (tutorService.checkIfTutorExists(username)) {
            return false;
        }
        return true;
    }

    @GetMapping("/email")
    private ResponseEntity checkEmail(@RequestParam String email) {
        if (tutorService.checkIfEmailExists(email)) {
            return ResponseEntity.badRequest().body("Duplicated");
        }
        return ResponseEntity.ok("Unique");
    }

    @GetMapping("/{tokenString}")
    private ResponseEntity<String> verifiyRegistrationToken(@PathVariable String tokenString) {
        UUID token = UUID.fromString(tokenString);

        if (userService.verifyToken(token)) {
            return ResponseEntity.ok("Verified");
        }
        return ResponseEntity.badRequest().body("Token not valid");
    }
}
