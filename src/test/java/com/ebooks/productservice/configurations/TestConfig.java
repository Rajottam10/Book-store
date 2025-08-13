package com.ebooks.productservice.configurations;

import com.ebooks.productservice.config.kafka.ProducerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    @Primary
    public KafkaTemplate<String, Object> mockKafkaTemplate() {
        return mock(KafkaTemplate.class);
    }

    // Or mock your specific producer service if you have one
    @Bean
    @Primary
    public ProducerService mockProducerService() {
        return mock(ProducerService.class);
    }
}
