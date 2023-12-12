package com.example.culturedge.web;

import com.example.culturedge.service.PhotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PhotoController {
    final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photo")
    public List<String> getPhotos(@RequestParam String name) {
        return photoService.getByName(name);
    }

    @PostMapping("/photo/add")
    public List<String> addPhoto(@RequestParam String name, @RequestParam String url) {
        return photoService.addByName(name,url);
    }

    @PostMapping("/photo/delete")
    public List<String> deletePhoto(@RequestParam String name, @RequestParam String url) {
        return photoService.deleteByName(name,url);
    }
}
