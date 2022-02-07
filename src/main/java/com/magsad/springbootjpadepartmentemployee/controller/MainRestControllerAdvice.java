package com.magsad.springbootjpadepartmentemployee.controller;

import com.magsad.springbootjpadepartmentemployee.controller.response.BaseResponse;
import com.magsad.springbootjpadepartmentemployee.exception.DepartmentNotFoundException;
import com.magsad.springbootjpadepartmentemployee.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MainRestControllerAdvice
    //  extends ResponseEntityExceptionHandler
{

    //Department
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<BaseResponse<String>> applicationException(DepartmentNotFoundException e){
        log.error(DepartmentNotFoundException.class.toString());
        log.error(e.getMessage());
        BaseResponse<String> baseResponse = new BaseResponse<String>();
        baseResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
    }

    //Employee
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<BaseResponse<String>> applicationException(EmployeeNotFoundException e){
        log.error(EmployeeNotFoundException.class.toString());
        log.error(e.getMessage());
        BaseResponse<String> baseResponse = new BaseResponse<String>();
        baseResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseResponse<String>>  exceptions(Throwable e) {
        log.error(Throwable.class.toString());
        log.error(e.getMessage());
        e.printStackTrace();
        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
        // return new ResponseEntity<> (setMessage (e.getMessage ()), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
