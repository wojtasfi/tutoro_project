package com.tutoro.rest;

import com.tutoro.service.TutorService;
import com.tutoro.service.UserService;
import lombok.Data;
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
    private UsernameExists checkUsername(@RequestParam String username) {
        if (!tutorService.checkIfTutorExists(username)) {
            return null;
        }

        return new UsernameExists();
    }

    @GetMapping("/email")
    private EmailExists checkEmail(@RequestParam String email) {
        if (!tutorService.checkIfEmailExists(email)) {
            return null;
        }
        return new EmailExists();
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

@Data
class UsernameExists {
    private boolean usernameExists = true;
}

@Data
class EmailExists {
    private boolean emailExists = true;
}
