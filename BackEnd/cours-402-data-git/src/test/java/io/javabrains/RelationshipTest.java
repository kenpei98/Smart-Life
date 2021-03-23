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
import io.javabrains.springbootstarter.relationship.Relationship;
import io.javabrains.springbootstarter.relationship.RelationshipService;


@RunWith(MockitoJUnitRunner.class)
public class RelationshipTest {
	@Mock
	Relationship relationship;

	@InjectMocks
	RelationshipService relationshipService;	
	
	
	
	
	@Test
	public void testAlarm() {
	
		RelationshipService relationshipService = mock(RelationshipService.class);
		when(relationshipService.getbyid(1)).thenReturn(new Relationship(1,1,3,0));
		Relationship relationship = relationshipService.getbyid(1);
		
		assertEquals("1", relationship.getId().toString());
		assertEquals("1", relationship.getOuserid().toString());
		assertEquals("3", relationship.getUserid().toString());
		assertEquals("0", relationship.getApproved().toString());

	}
	

		
		@Test
		public void testAllAlarm() {
		
		RelationshipService relationshipService = mock(RelationshipService.class);
		List<Relationship> list = new ArrayList<Relationship>();
		list.add(new Relationship(1,1,3,0));
		list.add(new Relationship(2,2,3,0));
		list.add(new Relationship(3,2,4,1));

		
		when(relationshipService.getAllRelationship()).thenReturn(list);
		
		List<Relationship> list2 = new ArrayList<Relationship>();
		list2 = relationshipService.getAllRelationship();
		
		assertEquals("1", list2.get(0).getId().toString());
		assertEquals("1", list2.get(0).getOuserid().toString());
		assertEquals("3", list2.get(0).getUserid().toString());
		assertEquals("0", list2.get(0).getApproved().toString());

		assertEquals("2", list2.get(1).getId().toString());
		assertEquals("2", list2.get(1).getOuserid().toString());
		assertEquals("3", list2.get(1).getUserid().toString());
		assertEquals("0", list2.get(1).getApproved().toString());

		assertEquals("3", list2.get(2).getId().toString());
		assertEquals("2", list2.get(2).getOuserid().toString());
		assertEquals("4", list2.get(2).getUserid().toString());
		assertEquals("1", list2.get(2).getApproved().toString());
		

	
		}
		
		
		
	
	
}