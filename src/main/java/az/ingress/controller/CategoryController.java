package az.ingress.controller;


import az.ingress.model.dto.requset.CategoryRequestDto;
import az.ingress.model.dto.response.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
public class CategoryController {



    @PostMapping("/add")
    public CategoryResponseDto add(@RequestBody CategoryRequestDto category) {
        return new CategoryResponseDto();
    }

    @GetMapping("/get/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return new CategoryResponseDto();
    }

    @PutMapping("/update")
    public CategoryResponseDto update(@RequestBody CategoryRequestDto category) {
        return new CategoryResponseDto();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        new CategoryResponseDto();
    }

    @GetMapping("/getAll")
    public List<CategoryResponseDto> getAll() {
        return new ArrayList<>();
    }



}