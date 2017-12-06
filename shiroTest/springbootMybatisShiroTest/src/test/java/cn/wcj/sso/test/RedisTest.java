package cn.wcj.sso.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisTest extends BaseJunitTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate  ;
	
	@Test
	public void testSet()throws Exception{
		stringRedisTemplate.opsForValue().set("msg", "coder520");
	}
	
}
