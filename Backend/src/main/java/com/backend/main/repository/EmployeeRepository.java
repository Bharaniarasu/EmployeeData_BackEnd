package com.backend.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.main.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
