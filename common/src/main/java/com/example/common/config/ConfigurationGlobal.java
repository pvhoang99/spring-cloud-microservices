package com.example.common.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationGlobal {

  @Bean
  public Gson gson() {
    return new Gson();
  }

}
