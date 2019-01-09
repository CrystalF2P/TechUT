package pl.mkrause.service;

import pl.mkrause.domain.Fish;
import pl.mkrause.domain.Fisherman;
import pl.mkrause.domain.Customer;
import pl.mkrause.domain.Card;

import java.util.List;

public interface FishStoreManager {


    void addFish(Fish fish);
    void updateFish(Fish fish);
    void deleteFish(long id);
    void deleteAllFish();
    Fish findFishById(long id);
    Fish findFishByGatunek(String gatunek);
    List<Fish> getAllFish();


    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(long id);
    void deleteAllCustomers();
    Customer findCustomerById(long id);
    List<Customer> getAllCustomers();


    void addFisherman(Fisherman fisherman);


    void addCard(Card card);

    
    
    
    
    //Polaczenia
    List<Fish> getBoughtFish(Customer customer); //wszystkie ryby kupione przez wybranego klienta
    List<Fisherman> getCaughtFish(Fish fish); //wszystkie ryby zlapane przez wybranego rybaka
    void buyFish(long customerId, long fishId); //laczy klienta z ryba
    void obtainCard(long cardId, long fishermanId); //laczy karte z rybakiem
    void catchFish(long fishermanId, long fishId); //laczy rybaka z ryba

}
