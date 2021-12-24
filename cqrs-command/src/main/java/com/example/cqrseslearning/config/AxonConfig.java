package com.example.cqrseslearning.config;

import com.example.cqrseslearning.account.aggregate.AccountBank;
import org.axonframework.common.jdbc.ConnectionProvider;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jdbc.EventTableFactory;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.MySqlEventTableFactory;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

  @Bean
  public EventSourcingRepository<AccountBank> accountAggregateEventSourcingRepository(EventStore eventStore){
    EventSourcingRepository<AccountBank> repository = EventSourcingRepository.builder(AccountBank.class).eventStore(eventStore).build();
    return repository;
  }

  @Autowired
  public void createJdbcEventStorageSchema(EventStorageEngine eventStorageEngine){
    ((JdbcEventStorageEngine) eventStorageEngine).createSchema(eventTableFactory());
  }

  @Bean
  public EventTableFactory eventTableFactory() {
    return new MySqlEventTableFactory();
  }

  @Bean
  public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
    return EmbeddedEventStore.builder()
        .storageEngine(storageEngine)
        .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
        .build();
  }

  @Bean
  public EventStorageEngine storageEngine(Serializer defaultSerializer,
      PersistenceExceptionResolver persistenceExceptionResolver,
      @Qualifier("eventSerializer") Serializer eventSerializer,
      AxonConfiguration configuration,
      TransactionManager transactionManager,
      ConnectionProvider connectionProvider) {
    return JdbcEventStorageEngine.builder()
        .snapshotSerializer(defaultSerializer)
        .upcasterChain(configuration.upcasterChain())
        .persistenceExceptionResolver(persistenceExceptionResolver)
        .eventSerializer(eventSerializer)
        .transactionManager(transactionManager)
        .connectionProvider(connectionProvider)
        .build();

  }
}