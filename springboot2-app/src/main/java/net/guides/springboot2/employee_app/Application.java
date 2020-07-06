package net.guides.springboot2.employee_app;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.guides.springboot2.employee_app.model.Book;
import net.guides.springboot2.employee_app.repository.EmployeeRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String...args) throws Exception {

        // Create an employee
        Book employee = new Book();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john@doe.com");
        employeeRepository.save(employee);
   
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}