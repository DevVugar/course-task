package az.ingress.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
    private Long id;

    private String name;

    private String description;

    private double price;
}
