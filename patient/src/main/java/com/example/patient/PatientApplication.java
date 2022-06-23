package com.example.patient;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringCloudApplication
@EnableOAuth2Sso
@EnableFeignClients
@EnableConfigurationProperties
@ConfigurationPropertiesScan(value = "com.example.*")
public class PatientApplication {

  public static void main(String[] args) {
    SpringApplication.run(PatientApplication.class, args);
  }


  @Configuration
  public static class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
      builder.serializerByType(ObjectId.class, new ToStringSerializer());
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(
          builder.build());
      converters.add(converter);
      super.configureMessageConverters(converters);
    }
  }
}
