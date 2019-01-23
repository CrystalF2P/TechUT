package pl.mkrause.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.mkrause.domain.Fish;
import pl.mkrause.domain.Fisherman;
import pl.mkrause.domain.Customer;
import pl.mkrause.domain.Card;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class FishStoreManagerHibernateImpl implements FishStoreManager {

    @Autowired
    private SessionFactory hsf;

    @Override
    public void addFish(Fish fish) {
        hsf.getCurrentSession().save(fish);
    }

    @Override
    public void updateFish(Fish fish) {
        hsf.getCurrentSession().update(fish);
    }

    @Override
    public void deleteFish(long id) {
        Fish fish = findFishById(id);

        hsf.getCurrentSession().delete(fish);
    }

    @Override
    public void deleteAllFish() {
        hsf.getCurrentSession().createQuery("delete from Fish").executeUpdate();
    }

    @Override
    public Fish findFishById(long id) {
        return (Fish) hsf.getCurrentSession().get(Fish.class, id);
    }

    @Override
    public Fish findFishByGatunek(String gatunek) {
        Query query = hsf.getCurrentSession().
                createQuery("from Fish where gatunek=:gatunek");
        query.setParameter("gatunek", gatunek);
        Fish fish = (Fish) query.uniqueResult();

        return fish;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Fish> getAllFish() {
        return hsf.getCurrentSession().getNamedQuery("fish.all").list();
    }

    @Override
    public void addCustomer(Customer customer) {
        hsf.getCurrentSession().save(customer);

    }

    @Override
    public void updateCustomer(Customer customer) {
        hsf.getCurrentSession().update(customer);

    }

    @Override
    public void deleteCustomer(long id) {
        Customer customer = findCustomerById(id);

        hsf.getCurrentSession().delete(customer);
    }

    @Override
    public void deleteAllCustomers() {
        hsf.getCurrentSession().createQuery("delete from Customer").executeUpdate();
    }

    @Override
    public Customer findCustomerById(long id) {
        return (Customer) hsf.getCurrentSession().get(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return hsf.getCurrentSession().getNamedQuery("customer.all").list();
    }

    @Override
    public List<Fish> getBoughtFish(Customer customer){
        customer = (Customer) hsf.getCurrentSession().get(Customer.class, customer.getId());

        List<Fish> fishes = new ArrayList<Fish>(customer.getFishes());

        return fishes;
    }

    @Override
    public List<Fisherman> getCaughtFish(Fish fish){
        fish = (Fish) hsf.getCurrentSession().get(Fish.class, fish.getId());

        List<Fisherman> fishermen = new ArrayList<Fisherman>(fish.getFishermen());

        return fishermen;
    }

    @Override
    public void buyFish(long customerId, long fishId){
        Customer customer = findCustomerById(customerId);
        Fish fish = findFishById(fishId);

        customer.getFishes().add(fish);
    }

    @Override
    public void obtainCard(long cardId, long fishermanId){
        Card card = (Card) hsf.getCurrentSession().get(Card.class, cardId);
        Fisherman fisherman = (Fisherman) hsf.getCurrentSession().get(Fisherman.class, fishermanId);

        fisherman.setCard(card);
    }

    @Override
    public void catchFish(long fishermanId, long fishId){
        Fisherman fisherman = (Fisherman) hsf.getCurrentSession().get(Fisherman.class, fishermanId);
        Fish fish = findFishById(fishId);

        fish.getFishermen().add(fisherman);
    }

    @Override
    public void addFisherman(Fisherman fisherman) {
        hsf.getCurrentSession().save(fisherman);
    }
    
    @Override
    public void updateFisherman(Fisherman fisherman) {
        hsf.getCurrentSession().update(fisherman);
    }

    @Override
    public void deleteFisherman(long id) {
        Fisherman fisherman = findFishermanById(id);

        hsf.getCurrentSession().delete(fisherman);
    }
    
    @Override
    public Fisherman findFishermanById(long id) {
        return (Fisherman) hsf.getCurrentSession().get(Fisherman.class, id);
    }

    @Override
    public void addCard(Card card) {
        hsf.getCurrentSession().save(card);
    }
    
    @Override
    public void updateCard(Card card) {
        hsf.getCurrentSession().update(card);
    }

    @Override
    public void deleteCard(long id) {
        Card card = findCardById(id);

        hsf.getCurrentSession().delete(card);
    }
    
    @Override
    public Card findCardById(long id) {
    	return (Card) hsf.getCurrentSession().get(Card.class, id);
    }
    
}
