package io.javabrains.springbootstarter.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//data service

//common repository =CrudRepository(ginaric type) contain the logic of any entity class
/**
 * 
 * @author Xin Wang
 *  a class of a user repository
 */
public interface UsersRepository extends JpaRepository<Users,Integer>{
	
	
	@Query(value = "select distinct users.id, users.username, users.password, users.gender, users.email, users.type\r\n" + 
			"from users inner join relationship on users.id = relationship.userid\r\n" + 
			"where ouserid =?1 and approved ='1'", nativeQuery = true)
    List<Users> findRelationshipOuser(String ouserid);
    
	@Query(value = "select distinct users.id, users.username, users.password, users.gender, users.email, users.type\r\n" + 
			"from users inner join relationship on users.id = relationship.ouserid\r\n" + 
			"where userid = ?1 and approved ='1'", nativeQuery = true)
    List<Users> findRelationshipUser(Integer userid);

}
