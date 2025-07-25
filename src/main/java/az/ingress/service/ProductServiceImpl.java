package az.ingress.service;

import az.ingress.enums.ProductStatus;
import az.ingress.mapper.ProductMapper;
import az.ingress.model.dto.requset.ProductRequestDto;
import az.ingress.model.dto.response.ProductResponseDto;
import az.ingress.model.entity.Product;
import az.ingress.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Primary
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryService;
    private final List<ProductResponseDto> list = new ArrayList<>();


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Product fetchProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product dont exist"));
    }

    @Override
    @Transactional
    public ProductResponseDto add(ProductRequestDto requestDto) {
        log.info("ActionLog.add start: {}", requestDto);
        Product product = ProductMapper.PRODUCT_MAPPER.toEntity(requestDto);

        Product res = productRepository.save(product);

        try {
            calculate();
            log.info("ActionLog.calculate() executed successfully.");
        } catch (Exception e) {
            log.error("ActionLog.calculate error occurred  : {}", e.getMessage(), e);
            throw e;
        }

        log.debug("ActionLog.Mapped Product entity: {}", product);

        ProductResponseDto response = ProductMapper.PRODUCT_MAPPER.toResponse(res);

        log.debug("ActionLog.add success id: {}", response.getId());
        return response;
    }


    @Override
    public List<ProductResponseDto> getAll() {
        log.info("ActionLog.getAll stating");
        return productRepository.findAll().stream().map((e) ->
                        ProductMapper.PRODUCT_MAPPER.toResponse(e))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getById(Long id) {
        log.info("ActionLog.getById method start id: {}", id);
        Product product = fetchProduct(id);
        ProductResponseDto response = ProductMapper.PRODUCT_MAPPER.toResponse(product);

        log.info("ActionLog.getById method success id: {}", id);
        return response;
    }

    @Override
    public void delete(Long id) {
        log.info("ActionLog.delete.start id: {}", id);

        Product product = fetchProduct(id);
        product.setProductStatus(ProductStatus.DELETED);
        productRepository.save(product);
        log.info("ActionLog.delete.success id: {}", id);
    }

    @Override
    public ProductResponseDto getByName(String name) {
        log.info("ActionLog.getByName.start name : {}", name);
        Product product = productRepository.getProductByName(name);
        if (product == null) {
            log.warn("ActionLog.No product found with name: {}", name);
            return null;
        }

        ProductResponseDto response = ProductMapper.PRODUCT_MAPPER.toResponse(product);

        log.info("ActionLog.getByName.success name : {}", name);
        return response;
    }

    @Override
    @Transactional
    public ProductResponseDto updateName(Long id, String name) {
        log.info("ActionLog.updateName.start id : {} with new name : {}", id,name);
        Product product = productRepository.getProductById(id);

        if (product == null) {
            log.error("No product found with ID: {}", id);
            return null;
        }

        product.setName(name);

        Product res = productRepository.save(product);

        categoryService.method();
        ProductResponseDto response = ProductMapper.PRODUCT_MAPPER.toResponse(product);

        log.info("ActionLog.updateName.success id : {} with new name : {}", id,name);
        return response;
    }





















    @Override
    @Transactional
    public ProductResponseDto updateName2(Long id, String name) {
        Product product = productRepository.getProductById(id);
        product.setName(name);

        Product res = productRepository.save(product);

        categoryService.method2();
        return ProductMapper.PRODUCT_MAPPER.toResponse(res);
    }

    @Transactional
    public void method() {

        productRepository.save(Product.builder()
                .name("computer1000")
                .price(1000)
                .description("for office")
                .build());

        throw new RuntimeException();
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void calculate() {
        System.out.println("calculate");
    }


}
