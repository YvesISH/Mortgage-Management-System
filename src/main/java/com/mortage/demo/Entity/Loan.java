// Loan.java
package com.mortage.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Principal must be a non-negative number")
    private int principal;

    @Min(value = 0, message = "Annual interest rate must be a non-negative number")
    private float annualInterestRate;

    @Min(value = 0, message = "Years must be a non-negative number")
    private byte years;

    private double mortgage;
}
