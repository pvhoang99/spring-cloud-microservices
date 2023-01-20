package com.example.common.domain;

import com.example.common.event.DomainEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

public abstract class AggregateRoot extends AbstractAuditing {

    @Transient
    protected List<DomainEvent> events = new ArrayList<>();

    protected void dispatch(DomainEvent event) {
        this.events.add(event);
    }

    @DomainEvents
    public List<DomainEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        this.events.clear();
    }

}
