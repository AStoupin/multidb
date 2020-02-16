package ru.stoupin.multidb.repository1;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.stoupin.multidb.domain1.UserInfo1;



@Repository
//@PersistenceContext(type = PersistenceContextType.EXTENDED)
public interface UserInfo1Repository extends CrudRepository <UserInfo1, String> {
	  // ...
}