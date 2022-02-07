package com.magsad.springbootjpadepartmentemployee.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private static final long serialVersionUID = -748956247024967638L;

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEP_GEN")
    @SequenceGenerator(name="DEP_GEN", sequenceName="DEP_SEQ",allocationSize = 1)
    private Long id;
//    @Column(unique = true)
    private String name;
//    @Column(unique = true)
    private String phone;

//    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employeeList = new ArrayList<>();
}
