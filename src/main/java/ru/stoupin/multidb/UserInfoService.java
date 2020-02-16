package ru.stoupin.multidb;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.stoupin.multidb.domain1.UserInfo1;
import ru.stoupin.multidb.domain2.UserInfo2;
import ru.stoupin.multidb.repository1.UserInfo1Repository;
import ru.stoupin.multidb.repository2.UserInfo2Repository;

@Service
public class UserInfoService {
	@Autowired
	private UserInfo1Repository userInfo1Repository;
	@Autowired
	private UserInfo2Repository userInfo2Repository;
	
	public String getInfo() {
		Optional<UserInfo1> ui1 = userInfo1Repository.findById("admin");
		Optional<UserInfo2> ui2 = userInfo2Repository.findById("admin");
		
		return ui1.get().getPassword() + ui2.get().getPassword();	
	}
}
