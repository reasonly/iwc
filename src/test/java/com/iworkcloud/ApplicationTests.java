package com.iworkcloud;

import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		User test_user=new User();
		test_user.setUsername("sjq");
		test_user.setPassword("123");
		User user=userMapper.findByEntity(test_user);
		System.out.println("ApplicationTests.contextLoads" + "----user值= " + user);
	}


}
