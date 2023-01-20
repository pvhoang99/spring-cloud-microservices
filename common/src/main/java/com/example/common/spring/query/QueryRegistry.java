package com.example.common.spring.query;

import com.example.common.exception.InternalServerException;
import com.example.common.query.Query;
import com.example.common.query.QueryHandler;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class QueryRegistry {

    private static final Logger logger = LoggerFactory.getLogger(QueryRegistry.class);
    private final ApplicationContext applicationContext;
    private final Map<Class<? extends Query>, QueryHandlerProvider> queryHandlers = new HashMap<>();


    public <Q extends Query<R>, R> QueryHandler<Q, R> get(Class<Q> queryClass) {
        try {
            QueryHandlerProvider<QueryHandler<Q, R>> handlerProvider = this.queryHandlers.get(queryClass);
            if (handlerProvider == null) {
                throw new InternalServerException("NotFound.QueryHandler", queryClass.getSimpleName());
            }

            return handlerProvider.get();
        } catch (BeansException e) {
            throw new InternalServerException("NotFound.QueryHandler", queryClass.getSimpleName());
        }
    }

    @Autowired
    public QueryRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.registerHandlers();
    }

    private void registerHandlers() {
        String[] names = this.applicationContext.getBeanNamesForType(QueryHandler.class);
        for (String name : names) {
            this.register(this.applicationContext, name);
        }
    }

    @SuppressWarnings("unchecked")
    private void register(ApplicationContext applicationContext, String name) {
        Class<QueryHandler<?, ?>> handlerClass = (Class<QueryHandler<?, ?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
        if (generics == null || generics.length == 0) {
            logger.warn(String.format("QueryBus: Unable to get generic type of class %s", name));
            return;
        }
        Class<? extends Query> queryType = (Class<? extends Query>) generics[0];
        this.queryHandlers.put(queryType, new QueryHandlerProvider(applicationContext, handlerClass));
    }

}
