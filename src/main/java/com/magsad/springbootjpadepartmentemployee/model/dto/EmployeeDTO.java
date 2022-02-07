package com.magsad.springbootjpadepartmentemployee.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    private Long departmentId;
}
