package com.cts.microservice.customer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan
@EnableAutoConfiguration
@Component
public class CustomerApp {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerApp(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@PostConstruct
	public void generateTestData() {
                customerRepository.save(new Customer("Aditya", "kumar", "aditya05193@gmail.com", "Hinjewadi", "Pune"));
		customerRepository.save(new Customer("Gaurav", "Thalpati", "gaurav.thalpati@gmail.com", "Wakad", "Pune"));
                customerRepository.save(new Customer("Devendra", "brahmankar", "devendra.brahmankar@gmail.com", "Wakad", "Pune"));
        }

	public static void main(String[] args) {
		SpringApplication.run(CustomerApp.class, args);
	}

}
