package com.example.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(5)
                        .failureRateThreshold(60.0F)
                        .permittedNumberOfCallsInHalfOpenState(5)
                        .waitDurationInOpenState(Duration.ofSeconds(10))
                        .build())
                .timeLimiterConfig(
                        TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
                .build());
    }

    @Component
    @Order(-2)
    public static class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

        public GlobalErrorWebExceptionHandler(GlobalErrorAttributes gea, ApplicationContext applicationContext,
                                              ServerCodecConfigurer serverCodecConfigurer) {
            super(gea, new WebProperties.Resources(), applicationContext);
            super.setMessageWriters(serverCodecConfigurer.getWriters());
            super.setMessageReaders(serverCodecConfigurer.getReaders());
        }

        // Rendering HTML or JSON.
        @Override
        protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
            return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
        }

        private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
            final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
            return ServerResponse.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(errorPropertiesMap));
        }
    }

    @Component
    public static class GlobalErrorAttributes extends DefaultErrorAttributes {

        @Override
        public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
            Throwable error = super.getError(request);
            Map<String, Object> map = super.getErrorAttributes(request, options);
            map.put("status", HttpStatus.BAD_REQUEST.value());
            map.put("message", error.getMessage());
            return map;
        }
    }
}
