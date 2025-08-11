package com.ebooks.productservice.healthChecks;

import com.ebooks.productservice.repositories.BookRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ProductCatalogHealthIndicator implements HealthIndicator {
    private BookRepository bookRepository;

    public ProductCatalogHealthIndicator(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        boolean catalogHealthy = checkCatalogStatus();
        if(catalogHealthy){
            return Health.up().withDetail("productCatalog", "Available").build();
        }
        else{
            return Health.down().withDetail("productCatalog","Unavaialble").build();
        }
    }

    public boolean checkCatalogStatus(){
        try{
            bookRepository.count();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

