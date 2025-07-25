package az.ingress.service;

import az.ingress.model.dto.requset.ProductRequestDto;
import az.ingress.model.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl2 implements ProductService{
    @Override
    public ProductResponseDto add(ProductRequestDto requestDto) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return null;
    }

    @Override
    public ProductResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProductResponseDto getByName(String name) {
        return null;
    }

    @Override
    public ProductResponseDto updateName(Long id, String name) {
        return null;
    }

    @Override
    public ProductResponseDto updateName2(Long id, String name) {
        return null;
    }
}
