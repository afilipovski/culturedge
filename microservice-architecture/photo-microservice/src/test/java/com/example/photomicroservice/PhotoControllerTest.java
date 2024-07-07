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
class PhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PhotoController photoController;

    @SneakyThrows
    @BeforeEach
    void addPhoto() {
        MockMultipartFile file = new MockMultipartFile(
                "file",                         // Name of the file attribute
                "image.jpg",                    // Original file name
                MimeTypeUtils.IMAGE_JPEG.getType(),   // Content type for JPEG image
                "mock file".getBytes()                      // Byte array of image data
        );
        photoController.setPhoto("test-only", file);
    }

    @SneakyThrows
    @AfterAll
    static void removePhoto() {
        String path = System.getProperty("user.dir") +
                "/images/" +
                UUID.nameUUIDFromBytes("test-only".getBytes()) + ".jpg";
        Files.delete(Path.of(path));
    }

    @Test
    void testGetPhotoExists() throws Exception {
        byte[] imageBytes = "mock file".getBytes();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/photo")
                        .param("name", "test-only")
                        .accept(MediaType.IMAGE_JPEG))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(imageBytes));
    }

    @Test
    void testGetPhotoNotExists() throws Exception {
        String STATIC_DIRECTORY = System.getProperty("user.dir") + "/static";
        File noneFound = new File(STATIC_DIRECTORY + "/none-found.jpg");
        try (FileInputStream fis = new FileInputStream(noneFound)) {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/photo")
                            .param("name", "testName")
                            .accept(MediaType.IMAGE_JPEG))
                    .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                    .andExpect(content().bytes(fis.readAllBytes()));
        }

    }
}

