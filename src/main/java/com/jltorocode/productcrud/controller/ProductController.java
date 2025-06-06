package com.jltorocode.productcrud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.jltorocode.productcrud.dto.ProductDto;
import com.jltorocode.productcrud.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "API REST para gestión de productos")
public class ProductController {

    private final ProductService service;

    @GetMapping
    @Operation(summary = "Obtener todos los productos")
    public List<ProductDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ProductDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo producto")
    public ProductDto create(@RequestBody ProductDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto por ID")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto por ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
