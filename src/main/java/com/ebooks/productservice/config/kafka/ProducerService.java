package com.ebooks.productservice.config.kafka;

import com.ebooks.productservice.dtos.BookResponseDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private NewTopic newTopic;

    public void sendMsgToTopic(BookResponseDto responseDto) {
        String message = responseDto.toString();
        kafkaTemplate.send(newTopic.name(), message);
        System.out.println("[KAFKA] Sent: "+message);
    }
}
