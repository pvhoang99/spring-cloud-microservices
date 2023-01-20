package com.example.common.spring.query;

import com.example.common.query.Query;
import com.example.common.query.QueryBus;
import com.example.common.query.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"unchecked"})
public class SpringQueryBus implements QueryBus {

    private final QueryRegistry registry;

    @Autowired
    public SpringQueryBus(QueryRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <Q extends Query<R>, R> R execute(Q query) {
        QueryHandler<Q, R> queryHandler = (QueryHandler<Q, R>) registry.get(query.getClass());
        return queryHandler.handle(query);
    }

}
