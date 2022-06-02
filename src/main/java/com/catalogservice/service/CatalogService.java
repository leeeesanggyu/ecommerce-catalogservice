package com.catalogservice.service;

import com.catalogservice.domain.entity.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalog();
}
