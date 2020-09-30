package com.provigil.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SubscriptionBeanTest {
	
	SubscriptionBean testObj;
	
	@Test
	public void testGetterAndSetter() {
		testObj = new SubscriptionBean();
		List<Map<Integer,Double>> mapList = new ArrayList<>();
		Map<Integer,Double> map = new HashMap<Integer, Double>();
		map.put(2500, (double) 5000);
		mapList.add(map);
		
		testObj.setArea(2500);
		assertEquals(testObj.getArea(), 2500);
		
		testObj.setId(1);
		assertEquals(testObj.getId(), 1);
		
		testObj.setLocation("Indoor");;
		assertEquals(testObj.getLocation(), "Indoor");
		
		testObj.setPlanType("Monthly");
		assertEquals(testObj.getPlanType(), "Monthly");
		
		testObj.setMap(mapList);
		assertNotNull(testObj.getMap());
	}

}
