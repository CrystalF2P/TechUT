package pl.mkrause.crud.service;

import pl.mkrause.crud.domain.Fish;

import java.sql.PreparedStatement;
import java.util.List;

public interface FishService {

	void clear();
    void addFish(Fish fish);
    Fish updateFish(Fish fish);
    void deleteFish(long id);

    List<Fish> getFish(PreparedStatement stmt);
    List<Fish> getAllFish();
    List<Fish> findFishById(long id);
    List<Fish> findFishByGatunek(String gatunek);

    void addAllFish(List<Fish> fishList);
}
