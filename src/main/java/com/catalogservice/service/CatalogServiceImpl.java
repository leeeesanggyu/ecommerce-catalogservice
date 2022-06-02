package com.catalogservice.service;

import com.catalogservice.Repository.CatalogRepository;
import com.catalogservice.domain.entity.CatalogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;
    private final Environment env;

    @Override
    public Iterable<CatalogEntity> getAllCatalog() {
        return catalogRepository.findAll();
    }
}
