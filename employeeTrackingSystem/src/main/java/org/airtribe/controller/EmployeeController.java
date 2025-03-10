package org.airtribe.controller;

import org.airtribe.exception.EmployeeManagementException;
import org.airtribe.model.Employee;
import org.airtribe.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            var employeeCreated = employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeCreated);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        try {
            boolean deleted = employeeService.deleteEmployeeById(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee with id does not exist");
            }
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("byName")
    public ResponseEntity<?> getEmployeeByName(@RequestParam("name") String name) {
        try {
            Employee employee = employeeService.findEmployeeByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error occurred while fetching employee : " + e.getMessage());
        }
    }

    @GetMapping("byDept")
    public ResponseEntity<?> getEmployeesByDept(@RequestParam("dept") String dept) {
        List<Employee> employeeList = employeeService.findEmployeeByDept(dept);
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws EmployeeManagementException {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
    }

    @ExceptionHandler(EmployeeManagementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmployeeMangementException(EmployeeManagementException ex) {
        return "Error:" + ex.getMessage();
    }

}
