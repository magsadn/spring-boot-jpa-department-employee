package com.magsad.springbootjpadepartmentemployee.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long id){
        super(String.format("Department not found with id=%s",id));
    }
}
