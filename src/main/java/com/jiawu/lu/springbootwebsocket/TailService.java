package com.jiawu.lu.springbootwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author wuzhong on 2017/9/18.
 * @version 1.0
 */
@Service
public class TailService {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 2000)
    public void test() {

        String msg = String.valueOf(System.currentTimeMillis());

        System.out.println(msg);

        simpMessagingTemplate.convertAndSend("/topic/tailfiles", msg);

    }

}
