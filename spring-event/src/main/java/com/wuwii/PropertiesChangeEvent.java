package com.wuwii;

import java.util.Date;
import java.util.Map;

/**
 * @author KronChan
 * @date 2019-07-14 22:52
 */
public class PropertiesChangeEvent {

  private final Date eventDateTime;
  private Map<String, String> properties;

  public PropertiesChangeEvent(Map<String, String> properties) {
    this.properties = properties;
    this.eventDateTime = new Date();
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }

  public Date getEventDateTime() {
    return eventDateTime;
  }


  @Override
  public String toString() {
    return "PropertiesChangeEvent{" +
        "properties=" + properties +
        ", eventDateTime=" + eventDateTime +
        '}';
  }
}
