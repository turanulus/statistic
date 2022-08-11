package com.clipboard.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private long id;

  @NotEmpty
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "salary")
  private BigDecimal salary;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "currency")
  private Currency currency;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "department")
  private Department department;

  @NotNull
  @Column(name = "on_contract")
  private boolean onContract;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "sub_department")
  private SubDepartment subDepartment;
}
