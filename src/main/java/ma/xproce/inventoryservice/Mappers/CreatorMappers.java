package ma.xproce.inventoryservice.Mappers;

import ma.xproce.inventoryservice.Dto.CreatorRequest;
import ma.xproce.inventoryservice.dao.entities.Creator;
import org.springframework.stereotype.Component;

@Component
public class CreatorMappers {
    // Method to map CreatorRequest to Creator entity
    public Creator toCreator(CreatorRequest creatorRequest) {
        return Creator.builder()
                .name(creatorRequest.getName())  // Set name
                .email(creatorRequest.getEmail())  // Set email
                .build();  // Create Creator object with the mapped fields
    }

    // Method to map Creator entity back to CreatorRequest
    public CreatorRequest toCreatorRequest(Creator creator) {
        return new CreatorRequest(
                creator.getName(),  // Set name from Creator entity
                creator.getEmail()  // Set email from Creator entity
        );
    }
}
