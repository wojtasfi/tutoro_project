package com.tutoro.controller;

import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/picture")
public class PictureController {


    private static Logger LOGGER = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private TutorService tutorService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(@RequestParam MultipartFile file, @RequestParam Long id,
                           RedirectAttributes redirectAttrs) throws IOException {
        Tutor tutor = tutorService.findOne(id);
        if (file.isEmpty() || !isImage(file)) {
            redirectAttrs.addFlashAttribute("error", "It is not a picture file!");
            return "redirect:/picture/upload?id=" + id;
        }

        if (file.getSize() > 512000) {
            redirectAttrs.addFlashAttribute("error", "File is too big!");
            return "redirect:/picture/upload?id=" + id;
        }

        File image = multipartToFile(file);
        byte[] byteImage = readContentIntoByteArray(image);

        tutor.setProfilePic(byteImage);

        tutorService.saveTutor(tutor);

        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(tutor.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }

        return "redirect:/tutor/profile/edit/" + encodedUsername;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage(@RequestParam Long id, Model model) {

        Tutor tutor = tutorService.findOne(id);
        LOGGER.info(tutor.toString());

        if (!model.containsAttribute("tutor")) {
            model.addAttribute("tutor", tutor);
        }
        return "uploadPicture";
    }


    @RequestMapping(value = "/profilepic", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadUserAvatarImage(@RequestParam("username") String username) throws IOException {
        Tutor tutor = tutorService.findByUsername(username);

        byte[] imageContent = tutor.getProfilePic();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        if (imageContent == null) {
            File file = new ClassPathResource("static/images/ninja.jpg").getFile();
            byte[] deafultPicture = readContentIntoByteArray(file);
            return new ResponseEntity<>(deafultPicture, headers, HttpStatus.OK);

        }
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipart.getBytes());
        fos.close();
        return convFile;
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    private static byte[] readContentIntoByteArray(File file) {
        byte[] bFile = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            fileInputStream.read(bFile);

        } catch (Exception e) {
            LOGGER.error("Could not read file", e);
        }
        return bFile;
    }

}
