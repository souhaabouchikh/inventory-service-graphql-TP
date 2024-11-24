package ma.xproce.inventoryservice.Dto;

import lombok.*;
import ma.xproce.inventoryservice.dao.entities.Creator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private Creator creator;

}
