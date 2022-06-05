package com.example.patient.config.security.listeners;

import com.example.patient.dao.entity.BaseEntity;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class BeforeSaveListener extends AbstractMongoEventListener<BaseEntity> {

  @Override
  public void onBeforeSave(BeforeSaveEvent<BaseEntity> event) {

    Date timestamp = new Date();

    if (event.getSource().getCreatedAt() == null) {
      event.getSource().setCreatedAt(timestamp);
    }
    event.getSource().setLastModified(timestamp);

    super.onBeforeSave(event);
  }
}
