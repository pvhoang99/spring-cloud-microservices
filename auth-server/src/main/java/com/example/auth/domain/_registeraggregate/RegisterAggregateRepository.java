package com.example.auth.domain._registeraggregate;

import com.example.auth.domain.role.Role;
import com.example.auth.domain.user.User;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterAggregateRepository {

    @Bean
    public Repository<User> userAggregateRepository(EntityManagerProvider entityManagerProvider, EventBus eventBus) {
        return GenericJpaRepository
            .builder(User.class)
            .entityManagerProvider(entityManagerProvider)
            .eventBus(eventBus)
            .build();
    }

    @Bean
    public Repository<Role> roleAggregateRepository(EntityManagerProvider entityManagerProvider, EventBus eventBus) {
        return GenericJpaRepository
            .builder(Role.class)
            .entityManagerProvider(entityManagerProvider)
            .eventBus(eventBus)
            .build();
    }

}
