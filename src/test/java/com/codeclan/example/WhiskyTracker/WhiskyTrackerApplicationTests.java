package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {

	}
	@Test
	public void whiskyByRegion(){
		assertEquals( 7, whiskyRepository.findWhiskyByDistilleryRegion("Highland").size());
	}

	@Test
	public void canFindDistilleryByWhiskyAgeGreaterThan(){
		assertEquals( 10, distilleryRepository.findDistilleryByWhiskiesAgeGreaterThan(12).size());
	}

	@Test
	public void canFindWhiskyByNameContaining(){
		assertEquals(4, whiskyRepository.findWhiskyByNameContaining("Glen").size());
	}

	@Test
	public void canFindDistilleryByWhiskyNameStartsWith(){
		assertEquals(5, distilleryRepository.findDistilleryByWhiskiesNameStartsWith("The").size());
	}

	@Test
	public void canFindDistilleryByWhiskiesNameStartsWith(){
		assertEquals( 5, whiskyRepository.findWhiskyByNameStartsWith("The").size());
	}

}
