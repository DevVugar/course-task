package az.ingress.service;

import az.ingress.mapper.CategoryMapper;
import az.ingress.model.dto.requset.CategoryRequestDto;
import az.ingress.model.dto.response.CategoryResponseDto;
import az.ingress.model.entity.Category;
import az.ingress.model.entity.Product;
import az.ingress.repository.CategoryRepository;
import az.ingress.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    @Override
    public CategoryResponseDto add(CategoryRequestDto dto) {
        Category category = categoryMapper.toEntity(dto);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto dto) {
        Category existing = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(dto.getName());
        // update other fields as needed
        Category updated = categoryRepository.save(existing);
        return categoryMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void method(){

        productRepository.save(Product.builder()
                .name("computer2000")
                .price(1000)
                .description("for office")
                .build());

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void method2() {
        productRepository.save(Product.builder()
                .name("computer3000")
                .price(1000)
                .description("for office")
                .build());
        throw new RuntimeException();
    }
}
