package org.airtribe.repository;


import org.airtribe.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT emp FROM Employee emp WHERE emp.name = :empName")
    Employee findByName(@Param("empName") String empName);

    @Query("SELECT emp FROM Employee emp WHERE emp.dept = :dept")
    List<Employee> findEmployeeByDept(@Param("dept") String dept);
}
