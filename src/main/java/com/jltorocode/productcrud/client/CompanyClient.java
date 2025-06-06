package com.jltorocode.productcrud.client;


import com.jltorocode.productcrud.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
@RequiredArgsConstructor
public class CompanyClient {

    private final RestTemplate restTemplate;

    public CompanyDto getCompanyById(Long companyId) {
        try {
            return restTemplate.getForObject("http://localhost:8082/api/companies/" + companyId, CompanyDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("La empresa con ID " + companyId + " no existe.");
        }
    }
}