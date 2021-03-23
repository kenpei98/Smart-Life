package io.javabrains.springbootstarter.alarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
/**
 * @author Xin Wang
 * a class for friend services
 */
public class AlarmService {
	
	
	@Autowired
	/**
	 * an instance variable for friend repository
	 */
	private AlarmRepository alarmRepository;
	
	/**
	 * get a list of friend list by a user id
	 * @param userId user id
	 * @return a list of friend list by a user id
	 */
	public List<Alarm> getAllAlarms(Integer userId){
		
		List<Alarm> alarms= new ArrayList<>();
		alarmRepository.findByUserid(userId)
		.forEach(alarms::add);
		
		return alarms;
		
	}
	
	
	public List<Alarm> getAllOuserAlarms(Integer ouserid){
		
		List<Alarm> alarms= new ArrayList<>();
		alarmRepository.findByOuserid(ouserid)
		.forEach(alarms::add);
		
		return alarms;
		
	}
	
	/**
	 * get a friend object by a user id and his/her friend id
	 * @param id friend id
	 * @return a friend object by a user id and his/her friend id
	 */
	public Alarm getAlarm(Integer id)
	{
		return alarmRepository.findById(id).get();
	}

	/**
	 * add a friend object
	 * @param friend object 
	 */
	public void addAlarm(Alarm alarm) {

		alarmRepository.save(alarm);
	}
	
	public void updateAlarm(Integer id, Alarm alarm) {

		alarmRepository.save(alarm);
	}


	/**
	 * delete user friend by friend user id
	 * @param id friend user id
	 */
	public void deleteAlarm(Integer id) {
		alarmRepository.deleteById(id);
	}

}
