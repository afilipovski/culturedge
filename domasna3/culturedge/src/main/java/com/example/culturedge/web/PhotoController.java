package com.example.culturedge.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PhotoController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images";
    @GetMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPhoto(@RequestParam String name) throws IOException {
        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        File f = new File(UPLOAD_DIRECTORY + "\\" + uuid + ".jpg");
        if (!f.exists())
            throw new ResponseStatusException(NOT_FOUND);
        try (FileInputStream fis = new FileInputStream(f)){
            return fis.readAllBytes();
        }
    }

    @PostMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] setPhoto(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {
        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, uuid+".jpg");
        Files.write(fileNameAndPath, file.getBytes());
        return getPhoto(name);
    }
}
