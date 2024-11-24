package ma.xproce.inventoryservice.Mappers;

import ma.xproce.inventoryservice.Dto.VideoRequest;
import ma.xproce.inventoryservice.dao.entities.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper{
    // Method to map VideoRequest to Video entity
    public Video toVideo(VideoRequest videoRequest) {
        return Video.builder()
                .name(videoRequest.getName())
                .url(videoRequest.getUrl())
                .description(videoRequest.getDescription())
                .datePublication(videoRequest.getDatePublication())
                .creator(videoRequest.getCreator()) // Assuming the creator is provided in the DTO
                .build();
    }

    // Method to map Video entity to VideoRequest DTO
    public VideoRequest toVideoRequest(Video video) {
        return new VideoRequest(
                video.getName(),
                video.getUrl(),
                video.getDescription(),
                video.getDatePublication(),
                video.getCreator() // Return the creator from the Video entity
        );
    }
}
