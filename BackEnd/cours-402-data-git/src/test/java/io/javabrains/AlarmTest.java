package io.javabrains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.javabrains.springbootstarter.alarm.Alarm;
import io.javabrains.springbootstarter.alarm.AlarmService;


@RunWith(MockitoJUnitRunner.class)
public class AlarmTest {
	@Mock
	Alarm alarm;

	@InjectMocks
	AlarmService alarmServices;	
	
	
	
	
	@Test
	public void testAlarm() {
	
		AlarmService alarms = mock(AlarmService.class);
		when(alarms.getAlarm(1)).thenReturn(new Alarm(1,1,"8:00 am",0,"1,2,3,4,5,6,7",0));
		Alarm alarm = alarms.getAlarm(1);
		
		assertEquals("1", alarm.getId().toString());
		assertEquals("1", alarm.getUserid().toString());
		assertEquals("8:00 am", alarm.getTime());
//		assertEquals("0", alarm.getRepeatable().toString());
//		assertEquals("1,2,3,4,5,6,7", alarm.getRepeatday());
		assertEquals("0", alarm.getType().toString());
	
	}
	

		
		@Test
		public void testAllAlarm() {
		
		AlarmService alarms = mock(AlarmService.class);
		List<Alarm> list = new ArrayList<Alarm>();
		list.add(new Alarm(1,1,"8:00 am",0,"1",0));
		list.add(new Alarm(2,1,"9:00 am",1,"1,2",1));
		list.add(new Alarm(3,2,"10:00 am",0,"1,2,3",0));
		list.add(new Alarm(4,2,"11:00 am",1,"1,2,3,4",1));
		
		when(alarms.getAllAlarms(1)).thenReturn(list);
		
		List<Alarm> list2 = new ArrayList<Alarm>();
		list2 = alarms.getAllAlarms(1);
		
		assertEquals("1", list2.get(0).getId().toString());
		assertEquals("1", list2.get(0).getUserid().toString());
		assertEquals("8:00 am", list2.get(0).getTime());
//		assertEquals("0", list2.get(0).getRepeatable().toString());
//		assertEquals("1", list2.get(0).getRepeatday());
		assertEquals("0", list2.get(0).getType().toString());
		
		assertEquals("2", list2.get(1).getId().toString());
		assertEquals("1", list2.get(1).getUserid().toString());
		assertEquals("9:00 am", list2.get(1).getTime());
//		assertEquals("1", list2.get(1).getRepeatable().toString());
//		assertEquals("1,2", list2.get(1).getRepeatday());
		assertEquals("1", list2.get(1).getType().toString());

		

	
		}
		
		
		
	
	
}