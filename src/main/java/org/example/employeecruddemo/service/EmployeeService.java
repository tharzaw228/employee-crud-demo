package org.example.employeecruddemo.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.employeecruddemo.dao.EmployeeDao;
import org.example.employeecruddemo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    public Employee findEmployeeBindId(int id) {
        return employeeDao.findById(id).
                orElseThrow(EntityNotFoundException::new);
    }

    public void updateEmployeeVersion2(int id, Employee employee) {
        if(employeeDao.existsById(id)) {
            employee.setId(id);
            employeeDao.save(employee);
        }else {
            throw new EntityNotFoundException("Employee with id" + id + "is not found.");
        }
    }

    @Transactional
    public void updateEmployee(int id, Employee employee) { // manage stage can change with setter
        if(employeeDao.existsById(id)) {
            Employee employee1 = employeeDao.findById(id).get();
            employee1.setId(id);
            employee1.setFirstName(employee.getFirstName());
            employee1.setLastName(employee.getLastName());
            employee1.setEmail(employee.getEmail());
            employee1.setPhone(employee.getPhone());
            employee1.setHiredDate(employee.getHiredDate());
            employee1.setSalary(employee.getSalary());
        }else  {
            throw new EntityNotFoundException("Employee with id" + id + "is not found.");
        }
    }


    public void deleteEmployee(int id) {
        employeeDao.deleteById(id);
    }

    public void insertEmployee(Employee employee) {
        employeeDao.save(employee);
    }


}
