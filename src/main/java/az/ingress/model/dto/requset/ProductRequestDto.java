package az.ingress.model.dto.requset;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDto {
    private Long id;

    private String name;

    private String description;

    private double price;

    private Long categoryId;
}
