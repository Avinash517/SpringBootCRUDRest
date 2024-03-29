package com.cloudsolv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cloudsolv.model.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}