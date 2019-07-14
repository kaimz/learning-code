package com.wuwii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author KronChan
 * @date 2019-07-14 23:00
 */
@Component
public class PropertiesChangeListener {

  private static final Logger log = LoggerFactory.getLogger(PropertiesChangeListener.class);

  /**
   * 注意, 如果是需要添加异步任务  @Async,最好是手动配置一个 taskExecutor 的任务线程池,方便追踪任务
   *
   * @param propertiesChangeEvent event
   */
  @EventListener
  @Async
  public void listen(PropertiesChangeEvent propertiesChangeEvent) {
    log.info(">>>>>>>>>>>>>>>> properties change event={}", propertiesChangeEvent);
  }

}
