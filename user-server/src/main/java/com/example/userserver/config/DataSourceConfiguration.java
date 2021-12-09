package com.example.userserver.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.ReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.function.TransactionalDatabaseClient;
import org.springframework.data.r2dbc.function.convert.MappingR2dbcConverter;
import org.springframework.data.r2dbc.function.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.support.R2dbcExceptionTranslator;
import org.springframework.util.Assert;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.example.userserver")
public class DataSourceConfiguration extends AbstractR2dbcConfiguration {

  @Value("${mysql.host}")
  private String mysqlHost;

  @Value("${mysql.port}")
  private Integer mysqlPort;

  @Value("${mysql.database-name}")
  private String databaseName;

  @Value("${mysql.username}")
  private String username;

  @Value("${mysql.password}")
  private String password;

  @Override
  public ConnectionFactory connectionFactory() {
    return getMySqlConnectionFactory();
  }

  private MySqlConnectionFactory getMySqlConnectionFactory() {

    return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
        .username(username)
        .password(password)
        .host(mysqlHost)
        .port(mysqlPort)
        .database(databaseName)
        .build());

  }

  @Bean(name = "transactionalDatabaseClient")
  public TransactionalDatabaseClient transactionalDatabaseClient(
      ReactiveDataAccessStrategy dataAccessStrategy,
      R2dbcExceptionTranslator exceptionTranslator) {
    Assert.notNull(dataAccessStrategy, "DataAccessStrategy must not be null!");
    Assert.notNull(exceptionTranslator, "ExceptionTranslator must not be null!");
    return TransactionalDatabaseClient.builder().connectionFactory(this.connectionFactory())
        .dataAccessStrategy(dataAccessStrategy).exceptionTranslator(exceptionTranslator).build();
  }


  @Bean
  public MappingR2dbcConverter converter(RelationalMappingContext mappingContext,
      R2dbcCustomConversions r2dbcCustomConversions) {
    Assert.notNull(mappingContext, "MappingContext must not be null!");
    BasicRelationalConverter converter = new BasicRelationalConverter(mappingContext, r2dbcCustomConversions);
    return new MappingR2dbcConverter(converter);
  }
}
