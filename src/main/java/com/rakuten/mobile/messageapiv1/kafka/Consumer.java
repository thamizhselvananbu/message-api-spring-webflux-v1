package com.rakuten.mobile.messageapiv1.kafka;

import com.rakuten.mobile.messageapiv1.model.Messages;
import com.rakuten.mobile.messageapiv1.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Log4j2
public class Consumer implements CommandLineRunner {

    @Autowired
    private final ReactiveKafkaConsumerTemplate<String, Messages> reactiveKafkaConsumerTemplate;

    @Autowired
    private final MessageRepository messageRepository;

    private Flux<Messages> listen() {
        return reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .doOnNext(consumerRecord ->
                        log.info("received key={}, value={} from topic={}, offset={}",
                                consumerRecord.key(),
                                consumerRecord.value(),
                                consumerRecord.topic(),
                                consumerRecord.offset())

                ).map(ConsumerRecord::value)
                .doOnNext(messageRepository::save)
                .doOnError(throwable ->
                        log.error("Consumer failed to consume messages {}",throwable.getMessage()));
    }


    @Override
    public void run(String... args) throws Exception {
        //We have to trigger consumption to replace kafka listeners functionality
        listen().subscribe();
    }
}
