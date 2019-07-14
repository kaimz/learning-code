package com.wuwii;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author KronChan
 * @date 2019-07-14 23:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesChangePublisherTest {

  @Autowired
  private PropertiesChangePublisher propertiesChangePublisher;

  @org.junit.Test
  public void publish() {
    propertiesChangePublisher.publish();
  }
}