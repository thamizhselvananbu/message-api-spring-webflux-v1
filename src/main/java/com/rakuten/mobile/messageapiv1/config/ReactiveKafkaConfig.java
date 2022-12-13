package com.rakuten.mobile.messageapiv1.config;

import com.rakuten.mobile.messageapiv1.model.Messages;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;

@Configuration
public class ReactiveKafkaConsumerConfig {

    private static final String TOPIC = "messages";

    @Bean
    public ReceiverOptions<String, Messages> kafkaReceiverOptions(KafkaProperties kafkaProperties) {
        ReceiverOptions<String, Messages> basicreceiverOptions =
                ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return basicreceiverOptions.subscription(Collections.singleton(TOPIC));
    }
}
