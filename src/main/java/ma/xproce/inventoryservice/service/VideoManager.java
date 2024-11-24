package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.Dto.VideoRequest;
import ma.xproce.inventoryservice.Mappers.VideoMapper;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository; // Adjust the import based on your package structure
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoManager implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
    private final VideoRepository videoRepository;

    @Autowired
    public VideoManager(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video save(VideoRequest videoRequest) {
        // Convert VideoRequest to Video entity using VideoMapper
        Video video = videoMapper.toVideo(videoRequest);
        return videoRepository.save(video);
    }


    @Override
    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        videoRepository.deleteById(id);
    }

    @Override
    public Video updateVideo(Video video) {
        // Find the existing video by the id in the request
        Optional<Video> existingVideo = videoRepository.findById(video.getId());

        if (existingVideo.isPresent()) {
            // If the video exists, update its fields
            Video video2 = existingVideo.get();
            video.setName(video.getName());
            video.setDescription(video.getDescription());
            video.setUrl(video.getUrl());
            // Update any other fields from videoRequest

            // Save the updated video back to the repository
            return videoRepository.save(video);
        } else {
            // If the video doesn't exist, return null or throw an exception
            return null;  // You could throw an exception or return a meaningful response
        }
    }

}