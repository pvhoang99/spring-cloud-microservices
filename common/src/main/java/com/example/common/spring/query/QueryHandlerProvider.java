package com.example.common.spring.query;

import com.example.common.query.QueryHandler;
import org.springframework.context.ApplicationContext;

public class QueryHandlerProvider<H extends QueryHandler<?, ?>> {

  private final ApplicationContext applicationContext;
  private final Class<H> type;

  QueryHandlerProvider(ApplicationContext applicationContext, Class<H> type) {
    this.applicationContext = applicationContext;
    this.type = type;
  }

  public H get() {
    return applicationContext.getBean(type);
  }

}
