package pl.adiks.springmockito.controller;

import org.springframework.web.bind.annotation.*;
import pl.adiks.springmockito.model.Employee;
import pl.adiks.springmockito.model.Response;
import pl.adiks.springmockito.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public Response addEmployee(@RequestBody Employee employee) {

        employeeRepository.save(employee);
        return new Response(employee.getId() + " inserted!", true);
    }

    @GetMapping
    public Response getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        return new Response("record counts: " + employees.size(), true);
    }
}
