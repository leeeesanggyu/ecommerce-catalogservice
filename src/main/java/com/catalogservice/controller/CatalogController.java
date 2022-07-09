package com.catalogservice.controller;

import com.catalogservice.domain.dto.CatalogRes;
import com.catalogservice.domain.entity.CatalogEntity;
import com.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;
    private final ModelMapper modelMapper;

    @GetMapping("/health_check")
    public String healthCheck() {
        return "It's Working in catalog-service on Port = " + env.getProperty("local.server.port");
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<CatalogRes>> getAllCatalog() {
        final Iterable<CatalogEntity> catalogs = catalogService.getAllCatalog();

        ArrayList<CatalogRes> result = new ArrayList<>();
        catalogs.forEach(c -> result.add(modelMapper.map(c, CatalogRes.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
