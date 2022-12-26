package com.rakuten.mobile.messageapiv1.kafka;

import com.rakuten.mobile.messageapiv1.model.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class Producer {
    private static final String TOPIC = "messages";
    @Autowired
    private final ReactiveKafkaProducerTemplate<String, Messages> reactiveKafkaProducerTemplate;

    public void sendMessage(String key, Messages messages) {
        log.info("Producer start sending messages={} topi={} key={}", messages, TOPIC, key);
        reactiveKafkaProducerTemplate.send(TOPIC, messages)
                .doOnSuccess(senderResult ->
                        log.info("producer sucessfully sent{}", senderResult.recordMetadata().offset()))
                .doOnError(throwable ->
                        log.info("producer failed to sent messages {}", throwable.getMessage()))
                .subscribe();
    }
}
