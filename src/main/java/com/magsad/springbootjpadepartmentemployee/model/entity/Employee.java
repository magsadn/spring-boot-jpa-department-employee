package com.magsad.springbootjpadepartmentemployee.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 8495817802073010928L;

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMP_GEN")
    @SequenceGenerator(name="EMP_GEN", sequenceName="EMP_SEQ",allocationSize = 1)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
//    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "department_id",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Department department;
}
