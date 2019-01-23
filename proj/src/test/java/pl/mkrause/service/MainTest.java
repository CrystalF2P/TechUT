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
import pl.mkrause.domain.Customer;
import pl.mkrause.domain.Fisherman;
import pl.mkrause.domain.Card;

import java.util.Date;
import java.util.List;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class MainTest {

    @Autowired
    FishStoreManager fsm;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void buyFishCheck(){
        Customer customer = new Customer("Elzbieta", "Rybnicka");

        fsm.addCustomer(customer);

        List<Fish> boughtFish = fsm.getBoughtFish(customer);
        int now = boughtFish.size();

        Fish fish1 = new Fish("okon", 14.55, new Date(2019, 01, 01));
        Fish fish2 = new Fish("sum", 12.25, new Date(2019, 01, 02));

        fsm.addFish(fish1);
        fsm.addFish(fish2);

        fsm.buyFish(customer.getId(), fish1.getId());
        fsm.buyFish(customer.getId(), fish2.getId());

        List<Fish> boughtFishAfter = fsm.getBoughtFish(customer);
        int after = boughtFishAfter.size();

        assertEquals(now + 2, after);
        assertEquals(fish1.getGatunek(),boughtFishAfter.get(after-2).getGatunek());
        assertEquals(fish2.getGatunek(), boughtFishAfter.get(after-1).getGatunek());
    }

    @Test
    public void obtainCardTest(){
        Fisherman fisherman = new Fisherman("Robert", "Splawik", new Date(2019, 01, 01));
        fsm.addFisherman(fisherman);

        Card card = new Card("AAA-" + fisherman.getId() +"-XYZ");
        fsm.addCard(card);

        fsm.obtainCard(card.getId(),fisherman.getId());

        assertEquals(fisherman.getCard().getId(), card.getId());
    }

    @Test
    public void catchFishCheck(){
        Fisherman fisherman1 = new Fisherman("Adam", "Kalarepa", new Date(2019, 01, 01));
        Fisherman fisherman2 = new Fisherman("Szymon", "Ancymon", new Date(2019, 01, 02));

        fsm.addFisherman(fisherman1);
        fsm.addFisherman(fisherman2);


        Fish fish1 = new Fish("Szczupak", 20.99, new Date(2019, 01, 03));
        Fish fish2 = new Fish("Rekin", 17.66, new Date(2019, 01, 04));

        fsm.addFish(fish1);
        fsm.addFish(fish2);

        List<Fisherman> fishermenOne = fsm.getCaughtFish(fish1);
        List<Fisherman> fishermenTwo = fsm.getCaughtFish(fish2);
        int One = fishermenOne.size();
        int Two = fishermenTwo.size();

        fsm.catchFish(fisherman1.getId(), fish1.getId());
        fsm.catchFish(fisherman1.getId(), fish2.getId());
        fsm.catchFish(fisherman2.getId(), fish1.getId());
        fsm.catchFish(fisherman2.getId(), fish2.getId());

        List<Fisherman> fishermenOneAfter = fsm.getCaughtFish(fish1);
        List<Fisherman> fishermenTwoAfter = fsm.getCaughtFish(fish2);
        int afterOne = fishermenOneAfter.size();
        int afterTwo = fishermenTwoAfter.size();

        assertEquals(One + 2, afterOne);
        assertEquals(Two + 2, afterTwo);
        assertEquals(fish1.getFishermen().get(afterOne-2).getImie(), fisherman1.getImie());
        assertEquals(fish1.getFishermen().get(afterOne-1).getImie(), fisherman2.getImie());
        assertEquals(fish2.getFishermen().get(afterTwo-2).getImie(), fisherman1.getImie());
        assertEquals(fish2.getFishermen().get(afterTwo-1).getImie(), fisherman2.getImie());
    }
}
