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
import io.javabrains.springbootstarter.users.Users;
import io.javabrains.springbootstarter.users.UsersService;


@RunWith(MockitoJUnitRunner.class)
public class UsersTest {
	@Mock
	Users users;

	@InjectMocks
	UsersService usersService;	
	
	
	
	
	@Test
	public void testoneUser() {
	
		UsersService userservice = mock(UsersService.class);
		when(userservice.getUser(1)).thenReturn(new Users(1,"xin","123456789","male","xin@gmail.com",0));
		Users user = userservice.getUser(1);
		
		assertEquals("1", user.getId().toString());
		assertEquals("xin", user.getName());
		assertEquals("123456789", user.getPassword());
		assertEquals("male", user.getGender());
		assertEquals("xin@gmail.com", user.getEmail());
		assertEquals("0", user.gettype().toString());
	
	}
	

		
		@Test
		public void testAllUser() {
		
		UsersService userservice = mock(UsersService.class);
		List<Users> list = new ArrayList<Users>();
		list.add(new Users(1,"xin","123456789","male","xin@gmail.com",0));
		list.add(new Users(2,"xin2","1234567","male","xin2@gmail.com",1));
		list.add(new Users(3,"xin3","12345","male","xin3@gmail.com",1));
		
		when(userservice.getAllUsers()).thenReturn(list);
		
		List<Users> list2 = new ArrayList<Users>();
		list2 = userservice.getAllUsers();
		
		assertEquals("1", list2.get(0).getId().toString());
		assertEquals("xin", list2.get(0).getName());
		assertEquals("123456789", list2.get(0).getPassword());
		assertEquals("male", list2.get(0).getGender());
		assertEquals("xin@gmail.com", list2.get(0).getEmail());
		assertEquals("0", list2.get(0).gettype().toString());
		
		assertEquals("2", list2.get(1).getId().toString());
		assertEquals("xin2", list2.get(1).getName());
		assertEquals("1234567", list2.get(1).getPassword());
		assertEquals("male", list2.get(1).getGender());
		assertEquals("xin2@gmail.com", list2.get(1).getEmail());
		assertEquals("1", list2.get(1).gettype().toString());
		
		assertEquals("3", list2.get(2).getId().toString());
		assertEquals("xin3", list2.get(2).getName());
		assertEquals("12345", list2.get(2).getPassword());
		assertEquals("male", list2.get(2).getGender());
		assertEquals("xin3@gmail.com", list2.get(2).getEmail());
		assertEquals("1", list2.get(2).gettype().toString());
	
		}
		
		

		
		
		
	
	
}