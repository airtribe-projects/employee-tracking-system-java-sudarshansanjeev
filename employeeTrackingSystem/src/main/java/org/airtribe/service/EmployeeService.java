package org.airtribe.service;

import org.airtribe.exception.EmployeeManagementException;
import org.airtribe.model.Employee;
import org.airtribe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Employee> findEmployeeByDept(String dept) {
        return employeeRepository.findEmployeeByDept(dept);
    }

    public Employee findEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Employee updateEmployee(Employee employee) throws EmployeeManagementException {
        Optional<Employee> savedEmployeeOptional = employeeRepository.findById(employee.getId());
        if (savedEmployeeOptional.isPresent()) {
            var savedEmployee = savedEmployeeOptional.get();
            savedEmployee.setName(employee.getName());
            savedEmployee.setDept(employee.getDept());
            savedEmployee.setProject(employee.getProject());
            employeeRepository.save(savedEmployee);
            return savedEmployee;
        }
        else {
            throw new EmployeeManagementException("Update failed! No employee found with ID : " + employee.getId());
        }
    }
}
