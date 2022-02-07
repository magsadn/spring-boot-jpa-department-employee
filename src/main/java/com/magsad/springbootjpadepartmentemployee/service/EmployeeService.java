package com.magsad.springbootjpadepartmentemployee.service;

import com.magsad.springbootjpadepartmentemployee.controller.response.BaseResponse;
import com.magsad.springbootjpadepartmentemployee.model.dto.EmployeeDTO;
import com.magsad.springbootjpadepartmentemployee.model.entity.Department;
import com.magsad.springbootjpadepartmentemployee.model.entity.Employee;
import com.magsad.springbootjpadepartmentemployee.exception.DepartmentNotFoundException;
import com.magsad.springbootjpadepartmentemployee.exception.EmployeeNotFoundException;
import com.magsad.springbootjpadepartmentemployee.repository.DepartmentRepository;
import com.magsad.springbootjpadepartmentemployee.repository.EmployeeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public BaseResponse<List<Employee>> findAll(){
        BaseResponse<List<Employee>> baseResponse = new BaseResponse<>();
        List<Employee> employees = employeeRepository.findAll();
        baseResponse.setData(employees);
        baseResponse.setMessage("Success");
        return baseResponse;
    }

    public BaseResponse<Employee> findById(Long id) {
//        Hospital hospital = null;
        BaseResponse<Employee> baseResponse = new BaseResponse<>();
        try {
            Employee employee = employeeRepository.findById(id).get();
            baseResponse.setData(employee);
            baseResponse.setMessage("Success");
        }catch (Exception e) {
            throw new EmployeeNotFoundException(id);
        }
        return baseResponse;
    }

    public BaseResponse<Employee> create(EmployeeDTO employeeDTO){
        BaseResponse<Employee> baseResponse = new BaseResponse<>();
        try {
            Department department = departmentService.getById(employeeDTO.getDepartmentId());
            Employee employee = new Employee();
            employee.setId(employeeDTO.getId());
            employee.setName(employeeDTO.getName());
            employee.setSurname(employeeDTO.getSurname());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhone(employeeDTO.getPhone());
            employee.setAddress(employeeDTO.getAddress());
            employee.setDepartment(department);

            baseResponse.setData(employeeRepository.save(employee));
            baseResponse.setMessage("Success");
        }catch (Exception e){
            throw new RuntimeException();
        }
        return baseResponse;
    }

    public BaseResponse<Employee> update(EmployeeDTO employeeDTO) {
        BaseResponse<Employee> baseResponse = new BaseResponse<>();
        try {
            Employee employeeForUpdate = getById(employeeDTO.getId());
            Department department = departmentService.getById(employeeDTO.getDepartmentId());
            employeeForUpdate.setName(employeeDTO.getName());
            employeeForUpdate.setSurname(employeeDTO.getSurname());
            employeeForUpdate.setEmail(employeeDTO.getEmail());
            employeeForUpdate.setPhone(employeeDTO.getPhone());
            employeeForUpdate.setAddress(employeeDTO.getAddress());
            employeeForUpdate.setDepartment(department);

            employeeRepository.save(employeeForUpdate);

            baseResponse.setData(employeeForUpdate);
            baseResponse.setMessage("Success");
    }catch (Exception e){
        throw new RuntimeException(e.getMessage());
    }
        return baseResponse;
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public boolean delete(Employee employee) {
        Employee employeeForDelete = getById(employee.getId());
        boolean status = false;
        if (employeeForDelete != null){
            employeeRepository.delete(employee);
            status = true;
        }
        return status;
    }

    public boolean deleteById(Long id) {
        Employee employeeForDelete = getById(id);
        boolean status = false;
        if (employeeForDelete != null){
            employeeRepository.deleteById(id);
            status = true;
        }
        return status;
    }

}
