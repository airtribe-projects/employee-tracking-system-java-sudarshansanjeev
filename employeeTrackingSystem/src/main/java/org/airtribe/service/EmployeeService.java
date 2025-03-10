package org.airtribe.service;

import org.airtribe.model.Employee;
import org.airtribe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) throws Exception{
        if (isEmpty(employee.getName())) {
            throw new IllegalArgumentException("Name is mandatory");
        }

        employeeRepository.save(employee);
        //employeeRepository.findById(employee.getId());
        return employee;
    }

    public boolean deleteEmployeeById(Long id) {
        if (id == 0) {
            throw new IllegalArgumentException("Invalid Employee ID");
        }

        employeeRepository.deleteById(id);

        return true;
    }
}
