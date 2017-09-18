package com.jiawu.lu.springbootwebsocket;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Configuration
@EnableWebSocketMessageBroker
@Service
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
        registry.addEndpoint("/tail").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        super.configureClientInboundChannel(registration);
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        System.out.println("on connect");

        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());

        System.out.println(headers);

    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {

        System.out.println("on disconnect");
    }

    @EventListener
    private void onSubscribeEvent(SessionSubscribeEvent subscribeEvent){

        System.out.println("on subscribeEvent");

        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(subscribeEvent.getMessage());

        System.out.println(headers);

    }


    @EventListener
    private void onDisSubscribeEvent(SessionUnsubscribeEvent disconnectEvent){

        System.out.println("on SessionUnsubscribeEvent");

        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(disconnectEvent.getMessage());

        System.out.println(headers);

    }

}