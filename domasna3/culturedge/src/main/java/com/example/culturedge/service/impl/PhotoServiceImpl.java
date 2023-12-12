package com.example.culturedge.service.impl;

import com.example.culturedge.repository.PhotoRepository;
import com.example.culturedge.service.PhotoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<String> getByName(String name) {
        return photoRepository.getByName(name);
    }

    @Override
    public List<String> addByName(String name, String url) {
        return photoRepository.addByName(name,url);
    }

    @Override
    public List<String> deleteByName(String name, String url) {
        return photoRepository.deleteByName(name,url);
    }
}
