package chooeat.reservation;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
	
	@Value("localhost")
	private String redisHost;
	
	@Value("6379")
	private int redisPort;
	
	@Bean
	public Jedis jedis() {return new Jedis(redisHost, redisPort);}

}
