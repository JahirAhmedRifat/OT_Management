package com.ibn.OT_Management_System.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
      //  registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();

        RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
        registry
                .addEndpoint("/ws")
                .setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
                // .setAllowedOrigins(frontendCallerHost);
                .setAllowedOrigins("*");
    }


}
