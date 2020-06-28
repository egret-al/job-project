package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 整合WebSocket的配置类
 * @EnableWebSocketMessageBroker：开启WebSocket代理
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/6/15 20:37
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * registry.enableSimpleBroker("/topic");
     * 设置消息代理的前缀，如果消息的前缀是“/topic”，就会将消息转发给消息代理（broker），再由
     * 消息代理将消息广播给当前连接的客户端
     *
     * registry.setApplicationDestinationPrefixes("/app");
     * 配置一个或多个前缀，通过这些前缀过滤出需要被注解方法处理的消息。例如，前缀为“/app”的destination可以
     * 通过@MessageMapping注解的方法处理，而其他destination（例如“/topic”，“/queue”）将被直接交给broker处理
     *
     * registry.enableSimpleBroker("/topic", "queue");
     * 增加“queue”，方便群发消息和点对点管理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * registry.addEndpoint("/chat").withSockJS();
     * 定义一个前缀为“/chat”的endPoint，并开启sockjs支持，sockjs可以解决浏览器对WebSocket的兼容性
     * 问题，客户端将通过1这里配置的URL来建立WebSocket连接
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}
