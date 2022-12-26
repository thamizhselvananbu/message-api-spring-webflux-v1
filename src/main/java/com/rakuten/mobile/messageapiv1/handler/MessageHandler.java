package com.rakuten.mobile.messageapiv1.handler;

import com.rakuten.mobile.messageapiv1.Exception.MessageApiException;
import com.rakuten.mobile.messageapiv1.kafka.Producer;
import com.rakuten.mobile.messageapiv1.model.Messages;
import com.rakuten.mobile.messageapiv1.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Log4j2
@RequiredArgsConstructor
public class MessageHandler {
    private final MessageRepository messageRepository;

    private final Producer producer;

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
        Flux<Messages> message = request.bodyToFlux(Messages.class);
        message.doOnNext(messages -> {
            try {
                if (messages.getTopic() == null)
                    throw new MessageApiException(1001, "Topic is empty", null);
                producer.sendMessage(messages.getUserId(), messages);
            } catch (MessageApiException e) {
                log.error(e.getMessage());
            }
        }).doOnError(throwable -> {
            log.info(throwable.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Processing error", null);
        }).subscribe();

        return ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON).build();
    }

    public Mono<ServerResponse> deleteMessageHandler(ServerRequest request) {
        messageRepository.deleteById(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
    }
}
