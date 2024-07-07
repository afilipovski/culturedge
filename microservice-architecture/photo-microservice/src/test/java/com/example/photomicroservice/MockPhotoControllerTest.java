package com.example.photomicroservice;

import com.example.photomicroservice.web.PhotoController;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhotoController.class)
class MockPhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhotoController photoController;

    @Test
    void testSetPhoto() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "fakeImageBytes".getBytes());
        byte[] imageBytes = "fakeImageBytes".getBytes();
        when(photoController.setPhoto(eq("testName"), any(MockMultipartFile.class)))
                .thenReturn(imageBytes);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/photo")
                        .file(mockFile)
                        .param("name", "testName"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(imageBytes));
    }
}

