package pl.adiks.springmockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.adiks.springmockito.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
