package com.wuwii;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author KronChan
 * @date 2019-07-14 22:59
 */
@Component
public class PropertiesChangePublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  public PropertiesChangePublisher(
      ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public void publish() {
    Map<String, String> properties = new HashMap<>();
    properties.put("p1", "1");
    properties.put("p2", "2");
    PropertiesChangeEvent propertiesChangeEvent = new PropertiesChangeEvent(properties);
    applicationEventPublisher.publishEvent(propertiesChangeEvent);
  }
}
