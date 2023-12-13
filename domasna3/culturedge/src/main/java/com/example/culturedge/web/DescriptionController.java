package com.example.culturedge.web;

import com.example.culturedge.service.DescriptionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DescriptionController {
    final DescriptionService descriptionService;

    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @GetMapping("/description")
    public String getDescription(@RequestParam String name) {
        String res = descriptionService.getByName(name);
        if (res == null)
            return "";
        return res;
    }

    @PostMapping("/description")
    public String setDescription(@RequestParam String name, @RequestParam String desc) {
        descriptionService.setByName(name,desc);
        return getDescription(name);
    }
}
