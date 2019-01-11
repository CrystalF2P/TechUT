package pl.mkrause.tests;

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
    FishShopManager fishShopManager;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addPlaneCheck() {
        Fish fish = new Fish("A330","XX-100", 250, new Date(115, 05, 15));

        fishShopManager.addFish(fish);

