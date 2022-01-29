package org.ssn.strategic.service;

import org.springframework.stereotype.Service;
import org.ssn.strategic.persistence.model.Employee;
import org.ssn.strategic.response.dto.MessageDTO;
@Service
public interface StrategicEmployee {

	public MessageDTO createStrategicEmp(Employee employee);
}
