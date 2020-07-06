package net.guides.springboot2.employee_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.employee_app.model.Book;
import net.guides.springboot2.employee_app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Book> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Optional<Book> getEmployeeById(Long employeeId) {
		return this.employeeRepository.findById(employeeId);
	}

	@Override
	public Book createEmployee(Book employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public Book updateEmployee(Book employeeDetails) {
		return this.employeeRepository.save(employeeDetails);
	}

	@Override
	public void deleteEmployee(Book employee) {
		this.employeeRepository.delete(employee);
	}

}
