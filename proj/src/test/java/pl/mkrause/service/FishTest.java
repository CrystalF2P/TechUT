package pl.mkrause.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.mkrause.domain.Fish;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class FishTest {

    @Autowired
    FishStoreManager fsm;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addFishCheck() {
       Fish fish = new Fish("okon", 14.55, new Date(2019, 01, 01));

        fsm.addFish(fish);

        long id = fish.getId();

        Fish newFish = (Fish) hsf.getCurrentSession().get(Fish.class, id);

        assertEquals(fish.getId(), newFish.getId());
    }
    
    @Test
    public void updateFishCheck() {
    	Fish fish = new Fish("okon", 14.55, new Date(2019, 01, 01));

        fsm.addFish(fish);

        String gatunek = "sledz";

        fish.setGatunek(gatunek);

        fsm.updateFish(fish);

        assertEquals(fish.getGatunek(), gatunek);
    }

    @Test
    public void deleteFishCheck() {
    	Fish fish = new Fish("okon", 14.55, new Date(2019, 01, 01));

    	fsm.addFish(fish);

        List<Fish> allFish = hsf.getCurrentSession().getNamedQuery("fish.all").list();
        int now = allFish.size();

        fsm.deleteFish(fish.getId());

        List<Fish> allFishAfter = hsf.getCurrentSession().getNamedQuery("fish.all").list();
        int after = allFishAfter.size();

        assertEquals(after, now - 1);
    }
    
    @Test
    public void getFishByIdCheck() {
        Fish fish = new Fish("okon", 14.55, new Date(2019, 01, 01));

        fsm.addFish(fish);

        
        Fish newFish = fsm.findFishById(fish.getId());

        assertEquals(fish.getId(), newFish.getId());
    }

    @Test
    public void getAllFishCheck() {
        List<Fish> allFish = fsm.getAllFish();
        int now = allFish.size();

        Fish fish = new Fish("okon", 14.55, new Date(2019, 01, 01));
        fsm.addFish(fish);

        List<Fish> allFishAfter = fsm.getAllFish();
        int after = allFishAfter.size();

        assertEquals(after, now + 1);
    }
    
    @Test
    public void deleteAllFishCheck() {
    	Fish fish = new Fish("okon", 14.55, new Date(2019, 01, 01));
        fsm.addFish(fish);

        List<Fish> allFish = fsm.getAllFish();
        int now = allFish.size();

        fsm.deleteAllFish();

        List<Fish> allFishAfter = fsm.getAllFish();
        int after = allFishAfter.size();

        assertEquals(after + now, now);
    }
    
    @Test
    public void getFishByGatunek() {
        Fish fish = new Fish("okon", 14.55, new Date(2019, 01, 01));

        fsm.addFish(fish);

        Fish newFish = fsm.findFishByGatunek(fish.getGatunek());

        assertEquals(fish.getId(), newFish.getId());
    }

  
}