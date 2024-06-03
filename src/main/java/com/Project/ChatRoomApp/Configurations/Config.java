package com.Project.ChatRoomApp.Configurations;
/*
 * @author : Mausam Raj
 * @Date : 14-05-2024
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        Registering the endpoint for WebSocket communication.
        registry.addEndpoint("/chatroom").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        Configuring message broker to enable message handling for "/topic".
        registry.enableSimpleBroker("/topic/chat");
//        Setting the application destination prefix for messages.
        registry.setApplicationDestinationPrefixes("/app/chat");
    }
}
