package com.example.common.utils;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

public class MessageUtils {
    public static <E> Mono<Message<E>> from(E body, Map<String, Object> headers) {
        return Mono.just(
                MessageBuilder.createMessage(body, new MessageHeaders(headers))
        );
    }
}
