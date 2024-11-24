package ma.xproce.inventoryservice.web;

import ma.xproce.inventoryservice.Dto.CreatorRequest;
import ma.xproce.inventoryservice.Dto.VideoRequest;
import ma.xproce.inventoryservice.Exceptions.CreatorNotFoundException;
import ma.xproce.inventoryservice.Mappers.CreatorMappers;
import ma.xproce.inventoryservice.Mappers.VideoMapper;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import ma.xproce.inventoryservice.service.CreatorManager;
import ma.xproce.inventoryservice.service.VideoManager;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;
    private CreatorMappers creatorMapper;
    private VideoMapper videoMapper;
    private CreatorManager creatorManager;
    private VideoManager videoManager;

    VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository, CreatorMappers creatorMapper, VideoMapper videoMapper) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.creatorMapper = creatorMapper;
        this.videoMapper = videoMapper;
    }
    @QueryMapping
    public List<Video> videoList(){
        return videoRepository.findAll();
    }
    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new CreatorNotFoundException(id));
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator){
        Creator creator1= Creator.builder().name(creator.getName()).email(creator.getEmail()).build();
        return creatorRepository.save(creator1);
//        return creatorRepository.save(creatorMapper.toCreator(creator)) ;
    }
    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video) {
        // Check if the creator exists; if not, create and save them
        Creator creator = creatorRepository.findByEmail(video.getCreator().getEmail())
                .orElseGet(() -> {
                    // If creator doesn't exist, create and save the new creator
                    return creatorRepository.save(Creator.builder()
                            .name(video.getCreator().getName())
                            .email(video.getCreator().getEmail())
                            .build());
                });

        // Map the VideoRequest to a Video entity and associate the creator
        Video videoDAO = Video.builder()
                .name(video.getName())
                .url(video.getUrl())
                .creator(creator) // Associate the creator
                .description(video.getDescription())
                .datePublication(video.getDatePublication())
                .build();

        // Save and return the video
        return videoRepository.save(videoDAO);
    }
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        // Simulate delay for a video update every second
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Create a new random Creator
                    CreatorRequest creatorRequest = CreatorRequest.builder()
                            .name("xxxx" + new Random().nextInt())  // Generate a random name
                            .email("xxxx@gmail.com")
                            .build();

                    // Save the Creator
                    Creator creator = creatorManager.save(creatorMapper.toCreator(creatorRequest));

                    // Find a video by ID (assuming the ID is 1L here)
                    Video video = videoManager.findById(1L)
                            .orElseThrow(() -> new RuntimeException("Video not found"));

                    // Update the video with the new creator
                    video.setCreator(creator);
                    videoManager.updateVideo(video);  // Persist the updated video

                    return video;  // Return the updated video to be emitted
                })
        );
    }


}
