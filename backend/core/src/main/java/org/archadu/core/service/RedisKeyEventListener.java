package org.archadu.core.service;
import org.archadu.core.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisKeyEventListener implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if(pattern == null) {
            return;
        }
        log.info("Received message: {} on channel: {}", new String(message.getBody()), new String(pattern));
        String event = new String(pattern);
        String key = new String(message.getBody());

        if(event.contains("expired")) {
            handleExpiredKey(key);
        } else if(event.contains("set")) {
            handleSetKey(key);
        }
    }

    private void handleExpiredKey(String key) {
        System.out.println("Key expired: " + key);
        // rabbitTemplate.convertAndSend(RabbitMqConfig.COVER_QUEUE, key);
    }

    private void handleSetKey(String key) {
        System.out.println("Key set: " + key);
        rabbitTemplate.convertAndSend(RabbitMqConfig.COVER_QUEUE, key);
    }


}
