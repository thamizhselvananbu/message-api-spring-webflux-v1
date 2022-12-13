package com.rakuten.mobile.messageapiv1.router;

import com.rakuten.mobile.messageapiv1.handler.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class MessageRouter {

    @Bean
    public RouterFunction<ServerResponse> routeMessageApi(MessageHandler handler) {
        return RouterFunctions.route(GET("/messages"), handler::getMessageHandler)
                .andRoute(GET("/messages/{id}"), handler::getMessageByIdHandler)
                .andRoute(POST("/messages"), handler::postMessageHandler)
                .andRoute(DELETE("/messages/{id}"), handler::deleteMessageHandler);
    }
}
