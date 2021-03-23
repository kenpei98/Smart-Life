package io.javabrains.springbootstarter.relationship;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.javabrains.springbootstarter.alarm.Alarm;
import io.javabrains.springbootstarter.users.Users;

//data service

//common repository =CrudRepository(ginaric type) contain the logic of any entity class
/**
 * 
 * @author Xin Wang
 *  a class of a history repository
 */
public interface RelationshipRepository extends JpaRepository<Relationship,Integer>{
	

    @Transactional
    List<Relationship> removeByOuseridAndUserid(Integer ouserid,Integer userid);
    
    @Transactional
    List<Relationship> removeByOuserid(Integer id);
    
    @Transactional
    List<Relationship> removeByUserid(Integer id);
    
    public List<Relationship> findByOuserid(Integer ouserid);
    
    public List<Relationship> findByUserid(Integer userid);
    
    

}
