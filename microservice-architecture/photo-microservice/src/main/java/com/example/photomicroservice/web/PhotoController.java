package com.example.photomicroservice.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@RestController
@RequestMapping("/api")
// Controller for handling photo-related requests.
public class PhotoController {

    // Directory where images are uploaded and retrieved from.
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images";
    public static String STATIC_DIRECTORY = System.getProperty("user.dir") + "/static";

    // Retrieves a photo based on the provided name.
    @GetMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPhoto(@RequestParam String name) throws IOException {

        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        File f = new File(UPLOAD_DIRECTORY + "/" + uuid + ".jpg");
        File noneFound = new File(STATIC_DIRECTORY + "/none-found.jpg");

        // If the requested image doesn't exist, return a default image.
        if (!f.exists())
            try (FileInputStream fis = new FileInputStream(noneFound)) {
                return fis.readAllBytes();
            }

        // Otherwise, return the requested image.
        try (FileInputStream fis = new FileInputStream(f)){
            return fis.readAllBytes();
        }
    }

    // Sets a photo for a given name.
    @PostMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] setPhoto(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {
        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, uuid+".jpg");
        Files.write(fileNameAndPath, file.getBytes());
        return getPhoto(name);
    }
}
