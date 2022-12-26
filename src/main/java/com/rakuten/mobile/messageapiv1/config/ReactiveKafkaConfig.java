package com.rakuten.mobile.messageapiv1.config;

import com.rakuten.mobile.messageapiv1.model.Messages;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.Collections;

@Configuration
public class ReactiveKafkaConfig {

    private static final String TOPIC = "messages";

    @Bean
    public ReceiverOptions<String, Messages> kafkaReceiverOptions(KafkaProperties properties) {
        ReceiverOptions<String, Messages> basicReceiver =
                ReceiverOptions.create(properties.buildConsumerProperties());
        return basicReceiver.subscription(Collections.singleton(TOPIC));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, Messages> consumerTemplate(ReceiverOptions<String, Messages> options) {
        return new ReactiveKafkaConsumerTemplate<>(options);
    }

    @Bean
    public ReactiveKafkaProducerTemplate<String, Messages> producerTemplate(KafkaProperties properties) {
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(properties.buildProducerProperties()));
    }
}
