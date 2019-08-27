package com.sk.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


//@Configuration
public class RedisConfig {

	private @Value("${spring.redis.host}") String redisHost;
	private @Value("${spring.redis.port}") int redisPort;

//	@Bean
//	public JedisConnectionFactory connectionFactory() {				
////		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
////		jedisConnectionFactory.setHostName(redisHost);
////		jedisConnectionFactory.setPort(redisPort);
////		jedisConnectionFactory.setUsePool(true);
////		
////		return jedisConnectionFactory;
//		
//		System.out.println("----redis config----");
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
//		return new JedisConnectionFactory(redisStandaloneConfiguration);
//	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(redisHost);
		factory.setPort(redisPort);
		//factory.setPassword(this.password);
		factory.setUsePool(true);
		factory.afterPropertiesSet();
		
		System.out.println("=----------------------redis host is "+redisHost);
		return factory;
	}
	
//	@Bean
//	RedisTemplate<?, ?> redisTemplate() {
//		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<Object, Object>();
//		
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		return redisTemplate;
//	}
	
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
////		redisTemplate.setKeySerializer(new StringRedisSerializer());
////		redisTemplate.setValueSerializer(new StringRedisSerializer());
//		
////		redisTemplate.setKeySerializer(new StringRedisSerializer());
////		redisTemplate.setValueSerializer(new StringRedisSerializer());
//		
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());	
//		
//		return redisTemplate;
//	}

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
			StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

			RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
			redisTemplate.setConnectionFactory(jedisConnectionFactory);
			redisTemplate.setKeySerializer(stringRedisSerializer);
			redisTemplate.setHashKeySerializer(stringRedisSerializer);

			redisTemplate.setValueSerializer(stringRedisSerializer);
			
			
			return redisTemplate;
	}
	
//	@Bean
//	RedisCacheManager cacheManager() {
//		
//		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//		return redisCacheManager;
//	}


	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}


