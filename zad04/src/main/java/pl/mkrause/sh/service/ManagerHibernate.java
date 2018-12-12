package pl.mkrause.sh.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.mkrause.sh.domain.Fish;
import pl.mkrause.sh.domain.Customer;

@Component
@Transactional
public class MangerHibernate implements Manager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addCustomer(Customer customer) {
		customer.setId(null);
		sessionFactory.getCurrentSession().persist(customer);
	}
	
	@Override
	public void deleteCustomer(Customer customer) {
		customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class,
				customer.getId());
		
		// lazy loading here
		for (Fish fish : customer.getFish()) {
			fish.setSold(false);
			sessionFactory.getCurrentSession().update(fish);
		}
		sessionFactory.getCurrentSession().delete(customer);
	}

	@Override
	public List<Fish> getBoughtFish(Customer customer) {
		customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class,
				customer.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Fish> pluralfish = new ArrayList<Fish>(customer.getFish());
		return pluralfish;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		return sessionFactory.getCurrentSession().getNamedQuery("customer.all")
				.list();
	}

	@Override
	public Customer findCustomerByVipId(int vipid) {
		return (Customer) sessionFactory.getCurrentSession().getNamedQuery("customer.byVipId").setInteger("vipId", vipid).uniqueResult();
	}


	@Override
	public Long addNewFish(Fish fish) {
		fish.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(fish);
	}

	@Override
	public void sellFish(Long customerId, Long fishId) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(
				Customer.class, customerId);
		Fish fish = (Fish) sessionFactory.getCurrentSession()
				.get(Fish.class, fishId);
		fish.setSold(true);
		customer.getFish().add(fish);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Fish> getAvailableFish() {
		return sessionFactory.getCurrentSession().getNamedQuery("fish.instock")
				.list();
	}
	@Override
	public void disposeFish(Customer customer, Fish fish) {

		customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class,
				customer.getId());
		fish = (Fish) sessionFactory.getCurrentSession().get(Fish.class,
				fish.getId());

		Fish toRemove = null;
		// lazy loading here (person.getCars)
		for (Fish aFish : customer.getFish())
			if (aFish.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			person.getCars().remove(toRemove);

		car.setSold(false);
	}

	@Override
	public Car findCarById(Long id) {
		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
	}

}
