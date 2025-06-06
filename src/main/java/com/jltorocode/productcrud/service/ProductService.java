package com.jltorocode.productcrud.service;

import com.jltorocode.productcrud.client.CompanyClient;
import com.jltorocode.productcrud.dto.ProductDto;
import com.jltorocode.productcrud.entity.Product;
import com.jltorocode.productcrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CompanyClient companyClient;

    public List<ProductDto> findAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductDto save(ProductDto dto) {
        // Validar que la company exista remotamente
        companyClient.getCompanyById(dto.getCompanyId());

        Product saved = repository.save(toEntity(dto));
        return toDto(saved);
    }

    public ProductDto update(Long id, ProductDto dto) {
        Product p = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        // Validar existencia de la company actualizada
        companyClient.getCompanyById(dto.getCompanyId());

        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        p.setCompanyId(dto.getCompanyId());

        return toDto(repository.save(p));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

private ProductDto toDto(Product p) {
    ProductDto dto = ProductDto.builder()
            .id(p.getId())
            .name(p.getName())
            .description(p.getDescription())
            .price(p.getPrice())
            .stock(p.getStock())
            .companyId(p.getCompanyId())
            .build();

    try {
        dto.setCompany(companyClient.getCompanyById(p.getCompanyId()));
    } catch (Exception e) {
        dto.setCompany(null); // o loggear error
    }

    return dto;
}
    private Product toEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .companyId(dto.getCompanyId())
                .build();
    }
}