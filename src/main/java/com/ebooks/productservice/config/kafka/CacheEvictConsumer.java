package com.ebooks.productservice.config.kafka;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CacheEvictConsumer {

    @CacheEvict(value = "products", key = "#bookId")
    public void evictCache(Long bookId) {
        System.out.println("Cache evicted for book ID: " + bookId);
    }

    @KafkaListener(topics = "cache-evict-topic", groupId = "product-service-group")
    public void listenAndEvict(String productId) {
        evictCache(Long.valueOf(productId));
    }
}

