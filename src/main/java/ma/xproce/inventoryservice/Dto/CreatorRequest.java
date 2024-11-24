package ma.xproce.inventoryservice.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorRequest {
    private String name;
    private String email;
}
