package org.ssn.strategic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssn.strategic.persistence.model.Employee;
import org.ssn.strategic.persistence.repository.EmployeeRepository;
import org.ssn.strategic.response.dto.MessageDTO;

@Service
public class StrategicEmployeeServiceImpl implements StrategicEmployee {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public MessageDTO createStrategicEmp(Employee employee) {
		MessageDTO messageDto = new MessageDTO();
		employeeRepository.save(employee);
		messageDto.setId(employee.getId());
		messageDto.setMessage("Strategic Employee Created");
		return messageDto;
	}
}
