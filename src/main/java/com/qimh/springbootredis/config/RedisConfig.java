package com.qimh.springbootredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 整合spring data redis 的配置类
 * 
 * @author qiminhui
 *
 */
@Configuration
public class RedisConfig {
	
	/**
	 * 1.创建JedisPoolConfig 对象：连接池参数
	 * @return
	 */
	@Bean
	public JedisPoolConfig getJedisPoolConfig(){
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
//		//最大空闲数
//		poolConfig.setMaxIdle(5);
//		//最小空闲数
//		poolConfig.setMinIdle(3);
//		//最大连接数
//		poolConfig.setMaxTotal(10);
		
		
		
		return poolConfig;
	}
	
	/**
	 * 2.创建JedisConnectionFactory ：配置redis 连接参数
	 */
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig poolConfig){
		
		JedisConnectionFactory  connectionFactory = new JedisConnectionFactory(); 
		
		//1.关联
		connectionFactory.setPoolConfig(poolConfig);
		//2.redis 主机地址
//		connectionFactory.setHostName("localhost");
//		//3.redis 端口
//		connectionFactory.setPort(6379);
//		//4.redis 数据的索引
//		connectionFactory.setDatabase(1);
		
		
		return connectionFactory;
	}
	
	/**
	 * 3.RedisTemplate: 用于执行reis的操作
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		//关联connectionFactory
		redisTemplate.setConnectionFactory(connectionFactory);
		
		//设置key的序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		
		
		
		
		return redisTemplate;
	}
	
	

}
