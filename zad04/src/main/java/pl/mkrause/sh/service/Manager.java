package pl.mkrause.sh.service;

import java.util.List;

import pl.mkrause.sh.domain.Fish;
import pl.mkrause.sh.domain.Customer;

public interface Manager {
	
	void addCustomer(Customer customer);
	List<Customer> getAllCustomers();
	void deleteCustomer(Customer customer);
	Customer findCustomerByVipId(int vipid);
	
	Long addNewFish(Fish fish);
	List<Fish> getAvailableFish();
	void disposeFish(Customer customer, Fish fish);
	Fish findFishById(Long id);

	List<Fish> getBoughtFish(Customer customer);
	void sellFish(Long customerId, Long fishId);

}
