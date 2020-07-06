package net.guides.springboot2.employee_app.service;

import java.util.List;
import java.util.Optional;

import net.guides.springboot2.employee_app.model.Book;

public interface EmployeeService {
	
	List<Book> getAllEmployees();

	Optional<Book> getEmployeeById(Long employeeId);

	Book createEmployee(Book employee);	

	Book updateEmployee(Book employeeDetails);

	void deleteEmployee(Book employee);
}
