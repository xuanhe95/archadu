package org.archadu.core;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@EnableRabbit
@SpringBootTest
class CoreApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(CoreApplicationTests.class);

	@Autowired
	AmqpAdmin amqpAdmin;


	@Test
	void contextLoads() {
	}

	@Test
	public void createExchange(){
		DirectExchange directExchange = new DirectExchange("cover.exchange", false, false);
		log.info("创建交换机" + directExchange.getName() + "成功");
		amqpAdmin.declareExchange(directExchange);
	}
	@Test
	public void createQueue(){
		Queue queue = new Queue("cover.queue", false, false, false);
		amqpAdmin.declareQueue(queue);
	}



}
