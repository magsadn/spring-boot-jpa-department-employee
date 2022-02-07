package com.magsad.springbootjpadepartmentemployee.controller;

import com.magsad.springbootjpadepartmentemployee.controller.response.BaseResponse;
import com.magsad.springbootjpadepartmentemployee.model.dto.EmployeeDTO;
import com.magsad.springbootjpadepartmentemployee.model.entity.Employee;
import com.magsad.springbootjpadepartmentemployee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "http://localhost:8088")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Employee>>> getAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<Employee>> getById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("query")
    public ResponseEntity<BaseResponse<Employee>> getByIdQuery(@RequestParam Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Employee>> create(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employeeDTO));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<Employee>> update(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(employeeDTO));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Employee>> delete(@RequestBody Employee employee) {
        if (!employeeService.delete(employee)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse<Employee>> deleteById(@PathVariable Long id){
        if (!employeeService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<BaseResponse<Employee>> deleteByIdQuery(
            @RequestParam Long id){
        if (!employeeService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
