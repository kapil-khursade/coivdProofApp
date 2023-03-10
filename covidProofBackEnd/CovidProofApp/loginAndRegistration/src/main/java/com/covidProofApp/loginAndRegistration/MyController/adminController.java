package com.covidProofApp.loginAndRegistration.MyController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidProodApp.Admin.Beans.centerInventory;
import com.covidProodApp.Admin.Beans.vaccineCenter;
import com.covidProodApp.Admin.Beans.vaccineDetails;
import com.covidProodApp.Admin.DTO.adminoutput;
import com.covidProodApp.Admin.Exceptions.centerException;
import com.covidProodApp.Admin.Exceptions.vaccineDetailException;
import com.covidProodApp.Admin.Services.adminAllServices;
import com.covidProofApp.loginAndRegistration.Beans.adminIdCard;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.DTO.adminDTO;
import com.covidProofApp.loginAndRegistration.DTO.output;
import com.covidProofApp.loginAndRegistration.Exeptions.adminExceptions;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;
import com.covidProofApp.loginAndRegistration.Service.adminServices;

@RestController
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	private adminServices adser;
	@Autowired
	private adminAllServices adallser;

	@PostMapping("/register")
	public ResponseEntity<adminIdCard> registerAdmin(@Valid @RequestBody adminIdCard admin) throws adminExceptions {
		
		return adser.registerAdmin(admin);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<adminIdCard> loginAdmin(@RequestBody adminDTO loginad) throws adminExceptions{
		
		return adser.loginAdmin(loginad);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<output> updatePassAdmin(@RequestBody adminDTO admin) throws adminExceptions{
		
		return adser.updatePassAdmin(admin);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<output> deleteAdmin(@PathVariable("id") Integer id) throws adminExceptions{
		
		return adser.deleteAdmin(id);
		
	}
	
	@PostMapping("/addVaccine")
	public ResponseEntity<adminoutput> addVaccine(@RequestBody vaccineDetails vaccine) throws vaccineDetailException{
		return adallser.addVaccine(vaccine);
	}
	
	@GetMapping("/allVaccines")
	public ResponseEntity<List<vaccineDetails>> getAllVaccine() throws vaccineDetailException{
		return adallser.getAllVaccies();
	}
	
	@DeleteMapping("/deleteVaccine/{id}")
	public ResponseEntity<adminoutput> deleteVaccine(@PathVariable("id") Integer id) throws vaccineDetailException{
		return adallser.deleteVaccine(id);
	}
	
	@GetMapping("/allApplicant")
	public ResponseEntity<List<applicantIdCard>> allApplicant() throws applicantException{
		return adser.getAllApplicant();
	}
	
	@PostMapping("/addCenter")
	public ResponseEntity<adminoutput> addVacCenter(@RequestBody vaccineCenter center) throws centerException{
		return adallser.addVaccineCenter(center);
	}
	
	@GetMapping("/allVacineCenter")
	public ResponseEntity<List<vaccineCenter>> getAllVaccCenter() throws centerException{
		return adallser.getAllVaccCenter();
	}
	
	@DeleteMapping("/deleteCenter/{id}")
	public ResponseEntity<adminoutput> deleteVacCenter(@PathVariable("id") Integer Id) throws centerException{
		return adallser.deleteVaccineCenter(Id);
	}	
	
	@PutMapping("/addCenterInventory/{id}")
	public ResponseEntity<adminoutput> addCenterInventory(@RequestBody centerInventory inve,@PathVariable("id")  Integer id) throws centerException{
		return adallser.addCenterInventory(inve, id);
	}
	
	@GetMapping("/getInvenotry/{id}")
	public ResponseEntity<List<centerInventory>> getInventory(@PathVariable("id") Integer id) throws centerException{
		return adallser.getCenterInventory(id);
	}
	
	@DeleteMapping("/deleteInventory/{id}")
	public ResponseEntity<adminoutput> deleteInventory(@PathVariable("id") Integer id) throws centerException{
		return adallser.deleteInventory(id);
	}
}
