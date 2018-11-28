package pl.mkrause.sh.service;

import java.util.List;

import pl.mkrause.sh.domain.Fish;
import pl.mkrause.sh.domain.Angler;

public interface Manager {
	
	void addFisherman(Angler angler);
	List<Angler> getAllFishermen();
	void deleteFisherman(Angler angler);
	Angler findAnglerByBoat(int boatnum);
	
	Long addNewFish(Fish fish);
	List<Fish> getAvailableFish();
	void disposeFish(Angler angler, Fish fish);
	Fish findFishById(Long id);

	List<Fish> getBroughtFish(Angler angler);
	void getFish(Long anglerId, Long fishId);

}
