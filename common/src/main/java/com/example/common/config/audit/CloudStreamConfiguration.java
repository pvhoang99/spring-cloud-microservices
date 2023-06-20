package com.example.common.config.audit;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class CloudStreamConfiguration {

  @Bean
  public MessageConverter providesTextPlainMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
