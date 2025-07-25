package az.ingress.controller;

import az.ingress.model.dto.requset.ProductRequestDto;
import az.ingress.model.dto.response.ProductResponseDto;
import az.ingress.repository.ProductRepository;
import az.ingress.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")

public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(@Qualifier("productServiceImpl") ProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }




    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponseDto> add(@RequestBody ProductRequestDto requestDto) {
        return new ResponseEntity<>(productService.add(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-name")
    ResponseEntity<ProductResponseDto> getByName(@RequestParam String name) {
        return new ResponseEntity<>(productService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<ProductResponseDto>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/updateName")
    ResponseEntity<ProductResponseDto> updateName(@PathVariable Long id, @RequestParam String name) {
        return new ResponseEntity<>(productService.updateName(id,name), HttpStatus.OK);
    }

    @PutMapping("/{id}/updateName2")
    ResponseEntity<ProductResponseDto> updateName2(@PathVariable Long id, @RequestParam String name) {
        return new ResponseEntity<>(productService.updateName2(id,name), HttpStatus.OK);
    }


}
