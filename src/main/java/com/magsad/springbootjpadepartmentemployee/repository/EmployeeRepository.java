package com.magsad.springbootjpadepartmentemployee.repository;

import com.magsad.springbootjpadepartmentemployee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByDepartmentId(Long departmentId);
}
