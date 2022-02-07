package com.magsad.springbootjpadepartmentemployee.service;

import com.magsad.springbootjpadepartmentemployee.controller.EmployeeController;
import com.magsad.springbootjpadepartmentemployee.controller.response.BaseResponse;
import com.magsad.springbootjpadepartmentemployee.model.entity.Department;
import com.magsad.springbootjpadepartmentemployee.exception.DepartmentNotFoundException;
import com.magsad.springbootjpadepartmentemployee.repository.DepartmentRepository;
import com.magsad.springbootjpadepartmentemployee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public BaseResponse<List<Department>> findAll(){
        BaseResponse<List<Department>> baseResponse = new BaseResponse<>();
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(
                d -> d.setEmployeeList(employeeRepository.findAllByDepartmentId(d.getId())));
        baseResponse.setData(departments);
        baseResponse.setMessage("Success");
        return baseResponse;
    }

    public BaseResponse<Department> findById(Long id) {
//        Hospital hospital = null;
        BaseResponse<Department> baseResponse = new BaseResponse<>();
        try {
            Department department = departmentRepository.findById(id).get();
            department.setEmployeeList(employeeRepository.findAllByDepartmentId(department.getId()));
            baseResponse.setData(department);
            baseResponse.setMessage("Success");
        }catch (Exception e) {
            throw new DepartmentNotFoundException(id);
        }
        return baseResponse;
    }

    public BaseResponse<Department> create(Department department){
        BaseResponse<Department> baseResponse = new BaseResponse<>();
        try {
            baseResponse.setData(departmentRepository.save(department));
            baseResponse.setMessage("Success");
     }catch (Exception e){
            throw new RuntimeException();
        }
        return baseResponse;
    }

    public BaseResponse<Department> update(Department department) {
        BaseResponse<Department> baseResponse = new BaseResponse<>();
    try {
        Department departmentForUpdate = getById(department.getId());
        departmentForUpdate.setName(department.getName());
        departmentForUpdate.setPhone(department.getPhone());
        departmentRepository.save(departmentForUpdate);
        baseResponse.setData(departmentForUpdate);
        baseResponse.setMessage("Success");
    }catch (Exception e){
//        RuntimeException();
        throw new RuntimeException(e.getMessage());
    }
        return baseResponse;
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }


    public boolean delete(Department department) {
        Department departmentForDelete = getById(department.getId());
        boolean status = false;
        if (departmentForDelete != null){
            departmentRepository.delete(department);
            status = true;
        }
        return status;
    }

    public boolean deleteById(Long id) {
        Department departmentForDelete = getById(id);
        boolean status = false;
        if (departmentForDelete != null){
            departmentRepository.deleteById(id);
            status = true;
        }
        return status;
    }
}
