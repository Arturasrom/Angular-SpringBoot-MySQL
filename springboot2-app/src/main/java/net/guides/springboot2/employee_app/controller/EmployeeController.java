package net.guides.springboot2.employee_app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import net.guides.springboot2.employee_app.beans.BookDTO;
import net.guides.springboot2.employee_app.exception.ResourceNotFoundException;
import net.guides.springboot2.employee_app.model.Book;
import net.guides.springboot2.employee_app.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
    private ModelMapper modelMapper;
	
	@GetMapping("/employees")
	public List<BookDTO> getAllEmployees() {
		List<Book> employees = employeeService.getAllEmployees();
		return employees.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<BookDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		Book employee = employeeService.getEmployeeById(employeeId).get();
		return ResponseEntity.ok().body(convertToDto(employee));
	}

	@PostMapping("/employees")
	public Book createEmployee(@Valid @RequestBody BookDTO employeeDTO) {
		return employeeService.createEmployee(convertToEntity(employeeDTO));
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Book> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Book employeeDetails) throws ResourceNotFoundException {
		Book employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Book updatedEmployee = employeeService.updateEmployee(employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Book employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeService.deleteEmployee(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public BookDTO convertToDto(Book employee) {
		BookDTO employeeDTO = modelMapper.map(employee, BookDTO.class);
		employeeDTO.setRole("ROLE_USER");
		return employeeDTO;
	}
	
	public Book convertToEntity(BookDTO employeeDTO) {
		Book employee = modelMapper.map(employeeDTO, Book.class);
		return employee;
	}
}
