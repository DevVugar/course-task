package az.ingress.mapper;


import az.ingress.model.dto.requset.ProductRequestDto;
import az.ingress.model.dto.response.ProductResponseDto;
import az.ingress.model.entity.Product;

public enum ProductMapper {

 PRODUCT_MAPPER;

 public Product toEntity(ProductRequestDto requestDto){
     return Product.builder()
                     .id(requestDto.getId())
                             .name(requestDto.getName())
                                     .description(requestDto.getDescription())
                                             .price(requestDto.getPrice()).build();
 }

    public ProductResponseDto toResponse(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
    }

}
