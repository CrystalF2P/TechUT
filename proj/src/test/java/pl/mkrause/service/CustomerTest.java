package pl.mkrause.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.mkrause.domain.Customer;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CustomerTest {

    @Autowired
    FishStoreManager fsm;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addCustomerCheck() {
        Customer customer = new Customer("Zenek", "Kliencki");

        fsm.addCustomer(customer);

        long id = customer.getId();

        Customer newCustomer = (Customer) hsf.getCurrentSession().get(Customer.class, id);

        assertEquals(customer.getId(), newCustomer.getId());
    }

    @Test
    public void updateCustomerCheck() {
        Customer customer = new Customer("Ruprecht", "Waza");

        fsm.addCustomer(customer);

        String name = "Waldemar";

        customer.setImie(name);

        fsm.updateCustomer(customer);

        assertEquals(customer.getImie(), name);
    }
    
    @Test
    public void deleteCustomerCheck() {
        Customer customer = new Customer("Alojzy", "Zakupek");

        fsm.addCustomer(customer);

        List<Customer> allCustomers = hsf.getCurrentSession().getNamedQuery("customer.all").list();
        int now = allCustomers.size();

        fsm.deleteCustomer(customer.getId());

        List<Customer> allCustomersAfter = hsf.getCurrentSession().getNamedQuery("customer.all").list();
        int after = allCustomersAfter.size();

        assertEquals(after, now - 1);
    }
    
    @Test
    public void getCustomerByIdCheck() {
        Customer customer = new Customer("Hania", "Kania");

        fsm.addCustomer(customer);

        Customer newCustomer = fsm.findCustomerById(customer.getId());

        assertEquals(customer.getId(), newCustomer.getId());
    }

    @Test
    public void getAllCustomersCheck() {
        List<Customer> allCustomers = fsm.getAllCustomers();
        int now = allCustomers.size();


        Customer customer = new Customer("Henryk", "Klepacz");
        fsm.addCustomer(customer);

        List<Customer> allCustomersAfter = fsm.getAllCustomers();
        int after = allCustomersAfter.size();

        assertEquals(after, now + 1);
    }

    @Test
    public void deleteAllCustomersCheck() {
        Customer customer = new Customer("Anna", "Wanna");
        fsm.addCustomer(customer);

        List<Customer> allCustomers = fsm.getAllCustomers();
        int now = allCustomers.size();

        fsm.deleteAllCustomers();

        List<Customer> allCustomersAfter = fsm.getAllCustomers();
        int after = allCustomersAfter.size();

        assertEquals(after + now, now);
    }
}
