package com.qimh.springbootredis;

import com.alibaba.fastjson.JSON;
import com.qimh.springbootredis.config.RedisLock;
import com.qimh.springbootredis.domain.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RunWith(SpringJUnit4ClassRunner.class)//@RunWith:让junit 和 spring 环境进行整合
@SpringBootTest(classes={SpringBootRedisApplication.class}) //@SpringBootTest:该类是一个springboot 测试类，加载springboot 启动器类
public class RedisTest {
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	//存入字符串:StringRedisSerializer
	@Test
	public void testSet(){
		
		redisTemplate.opsForValue().set("email", "237870348@qq.com");
	}
	//取出字符串:StringRedisSerializer
	@Test
	public void testGet(){
		
		String name  = (String) redisTemplate.opsForValue().get("email");
		System.out.println(name);
	}
	
	//存入JaveBea
	@Test
	public void testJavaBean(){
		Emp emp = new Emp();
		emp.setId(001);
		emp.setName("张三");
		emp.setGender("男");
		
		//重新设置value 的序列化器
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.opsForValue().set("emp", emp);
		
	}
	
	//取出JavaBean
	@Test
	public void testgetJavaBean(){
		
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		
		Emp emp = (Emp) redisTemplate.opsForValue().get("emp");
		
		System.out.println("emp="+ emp);
	}
	//以Json格式存入JavaBean
	@Test
	public void testSetJavaBean(){
		
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Emp.class));
		
		Emp emp = new Emp();
		emp.setId(002);
		emp.setName("李四");
		emp.setGender("男");
		redisTemplate.opsForValue().set("emp_json",emp);
	}

	//获取Json格式的JavaBean
	@Test
	public void testGetJavaBean(){

		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Emp.class));

		Emp emp = (Emp) redisTemplate.opsForValue().get("emp_json");

		System.out.println("emp=" + emp);
	}

	//以Json格式存入JavaBean
	@Test
	public void testSetJavaBeanUsedHash(){
		Emp emp = new Emp();
		emp.setId(002);
		emp.setName("李四");
		emp.setGender("男");
		redisTemplate.opsForHash().put("cash-emp","emp" ,JSON.toJSONString(emp));

	}
	//获取Json格式的JavaBean
	@Test
	public void testGetJavaBeanUsedHash(){

		Object emp = redisTemplate.opsForHash().get("cash-emp","emp");


		System.out.println("emp=" + emp);
	}
	



	//数据入队
	@Test
	public void testInQueue(){

		//重新设置value 的序列化器
//		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		ListOperations operation = redisTemplate.opsForList();
		for (int i = 0;i < 10;i++){
			Emp emp = new Emp();
			emp.setId(i);
			emp.setName("李四");
			emp.setGender("男");
			operation.leftPush("TEMPLATE_QUEUE" , String.valueOf(i));//JSON.toJSONString(emp)

		}

	}

	//数据出队
	@Test
	public void testOutQueue(){
		testInQueue();

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		ListOperations operation = redisTemplate.opsForList();

		for (int i = 0;i< 5;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
//					System.out.println("线程：" + Thread.currentThread().getName() +"读出队列数据:");
					RedisLock redisLock = new RedisLock(redisTemplate, "test");
					boolean isGetLock = redisLock.lockNoRetry();
					try {
						if (isGetLock){
							while (true){
								Object strJson = operation.rightPop("TEMPLATE_QUEUE");

								System.out.println("线程：" + Thread.currentThread().getName() + "-strJson="+strJson);
								if (strJson == null){
									break;
								}

							}
						}else {
							System.out.println("线程：" + Thread.currentThread().getName()+"提交频繁，请稍等！");
						}

						System.out.println("测试结束......");
					}catch (Exception ex){
						ex.printStackTrace();
					}finally {
						redisLock.unlock();
					}

				}
			});
		}


		executorService.shutdown();



	}

}
