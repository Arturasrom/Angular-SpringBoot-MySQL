package net.guides.springboot2.employee_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot2.employee_app.model.Book;

@Repository
public interface EmployeeRepository extends JpaRepository<Book, Long>{

}
