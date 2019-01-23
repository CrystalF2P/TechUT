package pl.mkrause.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.mkrause.domain.Fisherman;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class FishermanTest {

    @Autowired
    FishStoreManager fsm;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addFishermanTest(){
        Fisherman fisherman = new Fisherman("Andrzej", "Wendka", new Date(2019, 01, 01));

        fsm.addFisherman(fisherman);

        Fisherman newFisherman = (Fisherman) hsf.getCurrentSession().get(Fisherman.class, fisherman.getId());

        assertEquals(newFisherman.getId(), fisherman.getId());
    }
    
    @Test
    public void updateFishermanCheck() {
    	Fisherman fisherman = new Fisherman("Zygmunt", "Woda", new Date(2019, 01, 02));

        fsm.addFisherman(fisherman);

        String newName = "Doda";

        fisherman.setImie(newName);

        fsm.updateFisherman(fisherman);

        assertEquals(fisherman.getImie(), newName);
    }
    
    @Test
    public void deleteFishermanCheck() {
    	Fisherman fisherman = new Fisherman("Olgierd", "Splawik", new Date(2019, 01, 03));

    	fsm.addFisherman(fisherman);

        List<Fisherman> allFishermen = hsf.getCurrentSession().getNamedQuery("fisherman.all").list();
        int now = allFishermen.size();

        fsm.deleteFisherman(fisherman.getId());

        List<Fisherman> allFishermenAfter = hsf.getCurrentSession().getNamedQuery("fisherman.all").list();
        int after = allFishermenAfter.size();

        assertEquals(after, now - 1);
    }
    
    @Test
    public void getFishermanByIdCheck() {
    	Fisherman fisherman = new Fisherman("Michal", "Sieskichal", new Date(2019, 01, 04));

        fsm.addFisherman(fisherman);

        
        Fisherman newFisherman = fsm.findFishermanById(fisherman.getId());

        assertEquals(fisherman.getId(), newFisherman.getId());
    }
}
