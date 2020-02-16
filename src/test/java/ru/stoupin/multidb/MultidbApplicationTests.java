package ru.stoupin.multidb;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.stoupin.multidb.service.UserInfoService;

@SpringBootTest
class MultidbApplicationTests {
	@Autowired
	UserInfoService userInfoService;
	
	
	@Test
	void contextLoads() {
		assertNotNull(userInfoService.getInfo());
	}

}
