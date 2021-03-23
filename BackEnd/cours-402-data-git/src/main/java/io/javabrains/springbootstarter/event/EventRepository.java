package io.javabrains.springbootstarter.event;

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
public interface EventRepository extends JpaRepository<Event,Integer>{
	
		public List<Event> findByUserid(Integer userid);
		
	
}
