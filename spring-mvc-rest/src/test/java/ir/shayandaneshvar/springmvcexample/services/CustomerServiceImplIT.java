package ir.shayandaneshvar.springmvcexample.services;

import ir.shayandaneshvar.springmvcexample.api.v1.mapper.CustomerMapper;
import ir.shayandaneshvar.springmvcexample.api.v1.model.CustomerDTO;
import ir.shayandaneshvar.springmvcexample.bootstrap.Bootstrap;
import ir.shayandaneshvar.springmvcexample.domain.Customer;
import ir.shayandaneshvar.springmvcexample.repository.CategoryRepository;
import ir.shayandaneshvar.springmvcexample.repository.CustomerRepository;
import ir.shayandaneshvar.springmvcexample.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Customers : " + customerRepository.findAll().size());
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE,
                customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() {
        String name = "UpdatedName";
        Long id = getCustomerIdValue();
        Customer customer = customerRepository.getOne(id);
        assertNotNull(customer);
        String previousFirstName = customer.getFirstName();
        String previousLastName = customer.getLastName();
        CustomerDTO customerDTO = new CustomerDTO().setFirstName(name);
        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer = customerRepository.findById(id).get();
        assertNotNull(updatedCustomer);
        assertEquals(name, updatedCustomer.getFirstName());
        assertThat(previousLastName, equalTo(updatedCustomer.getLastName()));
        assertThat(previousFirstName, not(equalTo(customerDTO.getFirstName())));
    }

    @Test
    public void patchCustomerUpdateLastName() {
        String lastName = "UpdatedName";
        Long id = getCustomerIdValue();
        Customer customer = customerRepository.getOne(id);
        assertNotNull(customer);
        String previousFirstName = customer.getFirstName();
        String previousLastName = customer.getLastName();
        CustomerDTO customerDTO = new CustomerDTO().setLastName(lastName);
        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer = customerRepository.findById(id).get();
        assertNotNull(updatedCustomer);
        assertEquals(lastName, updatedCustomer.getLastName());
        assertThat(previousFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(previousLastName, not(equalTo(customerDTO.getLastName())));
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers found: " + customers.size());
        return customers.get(0).getId();
    }
}