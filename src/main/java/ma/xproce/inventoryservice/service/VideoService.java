package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dao.entities.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    Video save(Video video); // Method to save a video

    Optional<Video> findById(Long id); // Method to find a video by ID

    List<Video> findAll(); // Method to get all videos

    void deleteById(Long id); // Method to delete a video by
}
