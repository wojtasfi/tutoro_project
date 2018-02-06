package com.tutoro.rest;

import com.tutoro.entities.Tutor;
import com.tutoro.service.PictureService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/pictures")
public class PictureRest {


    private static Logger LOGGER = LoggerFactory.getLogger(PictureRest.class);

    @Autowired
    private TutorService tutorService;

    @Autowired
    private PictureService pictureService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> onUpload(@RequestParam MultipartFile file, @RequestParam Long id) throws IOException {
        Tutor tutor = tutorService.findOne(id);
        if (file.isEmpty() || !pictureService.isImage(file)) {
            return ResponseEntity.badRequest().body("It is not a picture file!");
        }

        if (file.getSize() > 512000) {
            return ResponseEntity.badRequest().body("File is too big!");
        }

        File image = pictureService.multipartToFile(file);
        byte[] byteImage = pictureService.readContentIntoByteArray(image);

        tutor.setProfilePic(byteImage);

        tutorService.saveTutor(tutor);

        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(tutor.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }

        return ResponseEntity.ok("Picture saved");
    }


    @GetMapping(value = "/profilepic")
    public ResponseEntity<byte[]> downloadUserAvatarImage(@RequestParam("username") String username) throws IOException {
        Tutor tutor = tutorService.findByUsername(username);

        byte[] imageContent = tutor.getProfilePic();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        if (imageContent == null) {
            File file = new ClassPathResource("static/images/ninja.jpg").getFile();
            byte[] deafultPicture = pictureService.readContentIntoByteArray(file);
            return new ResponseEntity<>(deafultPicture, headers, HttpStatus.OK);

        }
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }


}
