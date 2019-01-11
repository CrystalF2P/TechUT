package pl.mkrause.crud.service;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

import org.junit.Test;

import java.text.SimpleDateFormat;

import pl.mkrause.crud.domain.Fish;

public class FishManagerTest {
	
	
	CrudJDBC fishManager = new CrudJDBC();
	
	private final static String GATUNEK_1 = "Sledz";
	String DATAZLOWIENIA_1 = "20.09.2018";
	private final static double WAGA_1 = 14;
	
	private final static String GATUNEK_2 = "Okon";
	String DATAZLOWIENIA_2 = "10.04.2017";
	private final static double WAGA_2 = 12;
	
	private final static String GATUNEK_3 = "Sola";
	String DATAZLOWIENIA_3 = "22.11.2016";
	private final static double WAGA_3 = 1;
	
	private final static String GATUNEK_4 = "Sum";
	String DATAZLOWIENIA_4 = "02.09.2018";
	private final static double WAGA_4 = 14.5;
		
	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	
	java.util.Date date1 = (java.util.Date)format.parse(DATAZLOWIENIA_1);
	//java.util.Date date1 = new java.util.Date(); 
	//date1 = format.parse(DATAZLOWIENIA_1);
	Date sqldate1 = new Date(date1.getTime());
	
	java.util.Date date2 = (java.util.Date)format.parse(DATAZLOWIENIA_2);
	Date sqldate2 = new Date(date2.getTime());
	
	java.util.Date date3 = (java.util.Date)format.parse(DATAZLOWIENIA_3);
	//java.util.Date date3 = new java.util.Date();
	//date3 = format.parse(DATAZLOWIENIA_3);
	Date sqldate3 = new Date(date3.getTime());
	
	//java.util.Date date4 = new java.util.Date(); 
	//date4 = format.parse(DATAZLOWIENIA_4);
	Date sqldate4 = new Date(date4.getTime());
	
	
	
	Fish fish1 = new Fish(GATUNEK_1, sqldate1, WAGA_1);
	Fish fish2 = new Fish(GATUNEK_2, sqldate2, WAGA_2);
	Fish fish3 = new Fish(GATUNEK_3, sqldate3, WAGA_3);
	Fish fish4 = new Fish(GATUNEK_4, sqldate4, WAGA_4);
	
	
	@Test
	public void checkConnection(){
		assertNotNull(fishManager.getConnection());
	}
	
	//@Test
	public void checkAdding(){
		
		Fish fish = new Fish(GATUNEK_1, sqldate1, WAGA_1);
		
		fishManager.clearFish();
		assertEquals(1,fishManager.addFish(fish));
		
		List<Fish> pluralfish = fishManager.getAllFish();
		Fish fishRetrieved = pluralfish.get(0);
		
		assertEquals(GATUNEK_1, fishRetrieved.getGatunek());
		assertEquals(sqldate1, fishRetrieved.getDataZlowienia());
		assertEquals(WAGA_1, fishRetrieved.getWaga());
		
	}
	
	@Test
	public void checkAddAll(){
		fishManager.clearFish();		
		
		List<Fish> pluralfish = new ArrayList<>();
		pluralfish.add(fish1);
		pluralfish.add(fish2);
		//persons.add(person2);
		pluralfish.add(fish3);
		pluralfish.add(fish4);
		
		fishManager.addAllFish(pluralfish);
		int size = fishManager.getAllFish().size();
		
		//assertTrue(size == 0 || size == 4);
		
		assertThat(size, either(is(4)).or(is(0)));
		
	}

}
