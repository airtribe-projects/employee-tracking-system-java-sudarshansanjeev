package org.airtribe.service;

import org.airtribe.model.Employee;
import org.airtribe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        // add repository layer code;

        employeeRepository.save(employee);
        //employeeRepository.findById(employee.getId());
        return employee;
    }
}
