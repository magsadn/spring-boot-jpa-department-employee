package com.magsad.springbootjpadepartmentemployee.controller;

import com.magsad.springbootjpadepartmentemployee.controller.response.BaseResponse;
import com.magsad.springbootjpadepartmentemployee.model.entity.Department;
import com.magsad.springbootjpadepartmentemployee.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("departments")
@CrossOrigin(origins = "http://localhost:8088")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Department>>> getAll(){
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<Department>> getById(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @GetMapping("query")
    public ResponseEntity<BaseResponse<Department>> getByIdQuery(@RequestParam Long id){
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Department>> create(@RequestBody Department department){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.create(department));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<Department>> update(@RequestBody Department department){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.update(department));

    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Department>> delete(@RequestBody Department department) {
        if (!departmentService.delete(department)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse<Department>> deleteById(@PathVariable Long id){
        if (!departmentService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<BaseResponse<Department>> deleteByIdQuery(
            @RequestParam Long id){
        if (!departmentService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
