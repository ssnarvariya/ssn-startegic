package org.ssn.strategic.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ssn.strategic.persistence.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
