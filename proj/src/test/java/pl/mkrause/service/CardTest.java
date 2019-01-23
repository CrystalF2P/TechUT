package pl.mkrause.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.mkrause.domain.Card;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CardTest {

    @Autowired
    FishStoreManager fsm;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addCardTest(){
        Card card = new Card("AAA-123-XYZ");
        fsm.addCard(card);

        Card newCard = (Card) hsf.getCurrentSession().get(Card.class, card.getId());

        assertEquals(newCard.getCardId(), card.getCardId());
    }
    
    @Test
    public void updateCardCheck() {
        Card card = new Card("BBB-123-XYZ");

        fsm.addCard(card);

        String newId = "GGG-123-XYZ";

        card.setCardId(newId);

        fsm.updateCard(card);

        assertEquals(card.getCardId(), newId);
    }
    
    @Test
    public void deleteCardCheck() {
    	Card card = new Card("CCC-123-XYZ");

    	fsm.addCard(card);

        List<Card> allCards = hsf.getCurrentSession().getNamedQuery("card.all").list();
        int now = allCards.size();

        fsm.deleteCard(card.getId());

        List<Card> allCardsAfter = hsf.getCurrentSession().getNamedQuery("card.all").list();
        int after = allCardsAfter.size();

        assertEquals(after, now - 1);
    }
    
    @Test
    public void getCardByIdCheck() {
    	Card card = new Card("DDD-123-XYZ");

        fsm.addCard(card);

        
        Card newCard = fsm.findCardById(card.getId());

        assertEquals(card.getId(), newCard.getId());
    }
}
