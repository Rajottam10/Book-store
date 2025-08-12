package com.ebooks.productservice.config.kafka;

import com.ebooks.productservice.services.impl.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    private final ObjectMapper objectMapper;
    private final BookServiceImpl bookServiceImpl;

    public OrderEventConsumer(ObjectMapper objectMapper, BookServiceImpl bookServiceImpl) {
        this.objectMapper = objectMapper;
        this.bookServiceImpl = bookServiceImpl;
    }

    @KafkaListener(topics = "order-topic", groupId = "product-service-group")
    public void listenOrder(String message){
       String[] parts = message.split(",");
       Long bookId = Long.parseLong(parts[0]);
       int quantity = Integer.parseInt(parts[1]);
       bookServiceImpl.reduceBook(bookId, quantity);
        System.out.println("Stock reduced to bookId: "+ bookId);
    }
}
