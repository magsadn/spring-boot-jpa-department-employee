package com.magsad.springbootjpadepartmentemployee.repository;

import com.magsad.springbootjpadepartmentemployee.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
