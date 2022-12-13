package com.rakuten.mobile.messageapiv1.handler;

import com.rakuten.mobile.messageapiv1.model.Messages;
import com.rakuten.mobile.messageapiv1.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
@Log4j2
@RequiredArgsConstructor
public class MessageHandler {
    private final MessageRepository messageRepository;

    public Mono<ServerResponse> getMessageHandler(ServerRequest request) {
        Flux<Messages> message = messageRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(message, Messages.class);
    }

    public Mono<ServerResponse> getMessageByIdHandler(ServerRequest request) {
        Mono<Messages> message = messageRepository.findById(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(message, Messages.class);
    }

    public Mono<ServerResponse> postMessageHandler(ServerRequest request) {
        Flux<Messages> message = request.bodyToFlux(Messages.class).
                map(messages1 -> new Messages(null, messages1.getUserId(), messages1.getTopic(), messages1.getContent(), Instant.now()))
                .flatMap(messageRepository::save);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(message, Messages.class);
    }

    public Mono<ServerResponse> deleteMessageHandler(ServerRequest request) {
        messageRepository.deleteById(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
    }
}
