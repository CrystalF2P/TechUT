package pl.mkrause.crud.service;

import java.util.List;

import pl.mkrause.crud.domain.Fish;

public interface Crud {
	
	public int addFish(Fish fish);
	public List<Fish> getAllFish();
	
	
	public void addAllFish(List<Fish> fish);
	
}
