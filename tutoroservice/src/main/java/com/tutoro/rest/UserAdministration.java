package com.tutoro.rest;

import com.tutoro.dto.UserDto;
import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import com.tutoro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("users/")
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
            String token = userService.saveNewUser(userDto);
            return ResponseEntity.created(URI.create("/tutors/" + tutor.getUsername())).body("Verification token: " + token);

        } else {

            return ResponseEntity.badRequest().body("Duplicated tutor");
        }

    }


}
