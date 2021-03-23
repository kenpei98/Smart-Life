package io.javabrains.springbootstarter.alarm;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.users.Users;


@RestController
/**
 * 
 * @author Xin Wang
 * 
 * A class for user alarm controller
 */
public class AlarmController {
	
	
	@Autowired
	/**
	 * 
	 * instance variable for user alarm service
	 */
	private AlarmService alarmService;
	
	
	@RequestMapping("alarms/all/{userid}")
	/**
	 * get a list of alarms list by a user id
	 * @param id user id
	 * @return a list of alarms list by a user id
	 */
	public List<Alarm> getAllfriend(@PathVariable Integer userid) {
		return alarmService.getAllAlarms(userid);
	}
	
	@RequestMapping("alarms/all/ouserid/{ouserid}")
	/**
	 * get a list of alarms list by a user id
	 * @param id user id
	 * @return a list of alarms list by a user id
	 */
	public List<Alarm> getAllOuserAlarm(@PathVariable Integer ouserid) {
		return alarmService.getAllOuserAlarms(ouserid);
	}
	  
	
	@RequestMapping("alarms/{id}")	
	/**
	 * get a alarm object by a user id and his/her friend id
	 * @param id alarm id
	 * @return a alarm object by a user id and his/her friend id
	 */
	public Alarm getAlarm(@PathVariable Integer id) {	
		return alarmService.getAlarm(id);
	}
	
	
	@PostMapping(value="alarms/add")
	/**
	 * add a friend object by user id
	 * @param friend friend object 
	 * @param userid user id 
	 */
	public void addAlarm(@RequestBody Alarm alarm) {
		alarmService.addAlarm(alarm);
	}
	
	@PostMapping(value="alarms/add2")
	/**
	 * add a friend object by user id
	 * @param friend friend object 
	 * @param userid user id 
	 */
	public void addAlarm2(@RequestBody Alarm alarm) {
		alarmService.addAlarm(alarm);
	}
	
	
	@PutMapping(value="/alarms/add/{id}")
	public void updateAlarm(@RequestBody Alarm alarm, @PathVariable Integer id) {
		alarmService.updateAlarm(id,alarm);
	}

	
	
	@DeleteMapping("alarms/delete/{id}")	
	/**
	 * delete user friend by friend user id
	 * @param id friend user id
	 */
	public void deleteAlarm(@PathVariable Integer id) {	
		alarmService.deleteAlarm(id);
	}
}
