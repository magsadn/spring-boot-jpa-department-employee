package com.magsad.springbootjpadepartmentemployee.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id){
        super(String.format("Employee not found with id=%s",id));
    }
}
