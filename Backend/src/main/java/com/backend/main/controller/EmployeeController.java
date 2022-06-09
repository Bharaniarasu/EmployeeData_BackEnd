package com.backend.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.main.exception.ResourceNotFoundException;
import com.backend.main.model.Employee;
import com.backend.main.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/contact-us")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@PostMapping("employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not exists for ID No .  " + id));

		return ResponseEntity.ok(employee);

	}
	
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee enteredEmployeeData){
		Employee existingEmployeeData=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Data not exists for ID  .  "+id));
		
		existingEmployeeData.setFirstName(enteredEmployeeData.getFirstName());
		existingEmployeeData.setLastName(enteredEmployeeData.getLastName());
		existingEmployeeData.setMobile(enteredEmployeeData.getMobile());
		existingEmployeeData.setmailId(enteredEmployeeData.getmailId());
		
		Employee updatedEmployeeData=employeeRepository.save(existingEmployeeData);
		
		return ResponseEntity.ok(updatedEmployeeData);
	}
	
	@DeleteMapping("employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
	Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Data not Exists for ID - "+id));
	employeeRepository.delete(employee);
	Map <String, Boolean> response=new HashMap<>();
	response.put("Deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);

}
}