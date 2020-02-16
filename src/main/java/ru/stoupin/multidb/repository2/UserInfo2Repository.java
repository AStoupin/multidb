package ru.stoupin.multidb.repository2;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.stoupin.multidb.domain2.UserInfo2;



@Repository
//@PersistenceContext(type = PersistenceContextType.EXTENDED)
public interface UserInfo2Repository extends CrudRepository <UserInfo2, String> {
	  // ...
}