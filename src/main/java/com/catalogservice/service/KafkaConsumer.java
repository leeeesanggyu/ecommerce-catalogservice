package com.catalogservice.service;

import com.catalogservice.domain.entity.CatalogEntity;
import com.catalogservice.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;

    @KafkaListener(topics="order-topic")
    public void processMessage(String kafkaMessage) {
        log.info("kafka message => {}", kafkaMessage);
        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> map = new HashMap<>();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            log.error("kafka json process failed.", e);
        }

        final CatalogEntity entity = catalogRepository.findByProductId((String) map.get("productId"));
        entity.setStock(entity.getStock() - (Integer)map.get("qty"));
        catalogRepository.save(entity);
    }
}
