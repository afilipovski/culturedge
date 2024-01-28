package com.example.heritagemicroservice.web;

import com.example.heritagemicroservice.service.DescriptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
// Controller class for managing descriptions.
public class DescriptionController {
    final DescriptionService descriptionService;

    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    // HTTP GET endpoint for retrieving a description by name.
    @GetMapping("/description")
    public String getDescription(@RequestParam String name) {
        String res = descriptionService.getByName(name);
        if (res == null)
            return "";
        return res;
    }

    // HTTP POST endpoint for setting a description for a given name.
    @PostMapping("/description")
    public String setDescription(@RequestParam String name, @RequestParam String desc) {
        descriptionService.setByName(name,desc);
        return getDescription(name);
    }
}
