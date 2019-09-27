package ir.shayandaneshvar.springmvcexample.bootstrap;

import ir.shayandaneshvar.springmvcexample.domain.Category;
import ir.shayandaneshvar.springmvcexample.domain.Customer;
import ir.shayandaneshvar.springmvcexample.repository.CategoryRepository;
import ir.shayandaneshvar.springmvcexample.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomers();
        loadCategories();
    }

    private void loadCategories() {
        Category fruits = new Category().setName("Fruits");
        Category dried = new Category().setName("Dried");
        Category exotic = new Category().setName("Exotic");
        Category fresh = new Category().setName("Fresh");
        Category nuts = new Category().setName("Nuts");
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(exotic);
        categoryRepository.save(fresh);
        categoryRepository.save(nuts);
        System.out.println("Data Loaded | Categories | " + categoryRepository.count() +
                " |");
    }

    private void loadCustomers() {
        Customer customer = new Customer().setId(1l).setFirstName("Mike").setLastName(
                "Bond");
        customerRepository.save(customer);
        Customer customer1 = new Customer().setId(2l).setFirstName("Joe").setLastName(
                "Hammer");
        customerRepository.save(customer1);
        System.out.println("Data Loaded | Customers |" + customerRepository.count() +
                " |");
    }
}
