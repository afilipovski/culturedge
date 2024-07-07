package com.example.photomicroservice;

import com.example.photomicroservice.web.PhotoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhotoController.class)
class PhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhotoController photoController;

    @Test
    void testGetPhotoExists() throws Exception {
        byte[] imageBytes = "fakeImageBytes".getBytes();
        when(photoController.getPhoto("testName")).thenReturn(imageBytes);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/photo")
                        .param("name", "testName")
                        .accept(MediaType.IMAGE_JPEG))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(imageBytes));
    }

    @Test
    void testGetPhotoNotExists() throws Exception {
        byte[] noneFoundImageBytes = "noneFoundImageBytes".getBytes();
        when(photoController.getPhoto("testName")).thenReturn(noneFoundImageBytes);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/photo")
                        .param("name", "testName")
                        .accept(MediaType.IMAGE_JPEG))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(noneFoundImageBytes));
    }

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

