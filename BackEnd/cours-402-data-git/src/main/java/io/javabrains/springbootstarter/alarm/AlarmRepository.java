package io.javabrains.springbootstarter.alarm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//data service
//common repository =CrudRepository(ginaric type) contain the logic of any entity class
/**
 * 
 * @author Xin Wang
 *  a class of a friend repository
 */
public interface AlarmRepository extends JpaRepository<Alarm,Integer>{
	
		public List<Alarm> findByUserid(Integer userid);
		public List<Alarm> findByOuserid(Integer ouserid);
		
	
}
