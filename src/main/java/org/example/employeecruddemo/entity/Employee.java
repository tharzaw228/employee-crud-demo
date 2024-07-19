package org.example.employeecruddemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Fist Name cannot be empty")
    @Pattern(regexp = "[A-Za-z]*", message = "Last Name cannot be empty")
    private String firstName;
    @NotEmpty(message = "First Name cannot be empty")
    @Pattern(regexp = "[A-Za-z]*", message = "Last Name cannot be empty")
    private String lastName;

    @Email(message = "Invalid Email Format")
    private String email;


    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;


    @Past(message = "Hired Date must be past")
    @DateTimeFormat(pattern = "yyyy-MM--dd")
    private LocalDate hiredDate;

    @Min(value = 1000, message = "salary is too low")
    @Max(value = 3000, message = "salary is too high")
    private Double salary;
}
