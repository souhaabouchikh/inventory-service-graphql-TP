package ma.xproce.inventoryservice;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private VideoRepository videoRepository;
    @Bean
    CommandLineRunner start() {
        return args -> {
            // Create and save creators
            List<Creator> creators = List.of(
                    Creator.builder().name("Creator 1").build(),
                    Creator.builder().name("Creator 2").build()
            );
            creatorRepository.saveAll(creators);

            // Create and save videos
            List<Video> videos = List.of(
                    Video.builder().name("Video 1").creator(creators.get(0)).build(),
                    Video.builder().name("Video 2").creator(creators.get(1)).build()
            );
            videoRepository.saveAll(videos);
        };
    }

}
