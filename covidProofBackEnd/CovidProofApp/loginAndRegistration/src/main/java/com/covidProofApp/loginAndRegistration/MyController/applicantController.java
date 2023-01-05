package com.covidProofApp.loginAndRegistration.MyController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.Beans.appointment;
import com.covidProofApp.loginAndRegistration.Beans.doseStatus;
import com.covidProofApp.loginAndRegistration.DTO.applicantDTO;
import com.covidProofApp.loginAndRegistration.DTO.output;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;
import com.covidProofApp.loginAndRegistration.Service.applicantsServices;

@RestController
@RequestMapping("/applicant")
public class applicantController {
	
	@Autowired
	private applicantsServices appSer;

	@PostMapping("/register")
	public ResponseEntity<output> registerApplicant(@Valid @RequestBody applicantIdCard idCard) throws applicantException{
		return appSer.registerApplicant(idCard);
	}
	
	@PostMapping("/login")
	public ResponseEntity<applicantIdCard> loginApplicant(@RequestBody applicantDTO appLogDTO) throws applicantException{
		return appSer.loginApplicant(appLogDTO);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteApplicant(@PathVariable("id") Integer id) throws applicantException{
		return appSer.deleteApplicant(id);
	}
	

	@GetMapping("/getDoseStatus/{id}")
	public ResponseEntity<doseStatus> getDoseStatus(@PathVariable("id")  Integer id) throws applicantException{
		return appSer.getAppDoseStatus(id);
	}
	
	@PatchMapping("/updateDose/{id}")
	public ResponseEntity<output> updateDoseStatus(@PathVariable("id")  Integer id) throws applicantException{
		return appSer.updateDoseStatus(id);
	}
	
	@PostMapping("/addAppointment")
	public ResponseEntity<appointment> addAppointment(@RequestBody appointment app) throws applicantException{
		return appSer.addAppointmenr(app);
	}
	
	@DeleteMapping("/deleteAppointment/{id}")
	public ResponseEntity<output> deleteAppointment(@PathVariable("id") Integer id) throws applicantException{
		return appSer.deleteAppointment(id);
	}
	
	@GetMapping("/getAppointments/{id}")
	public ResponseEntity<List<appointment>> getAllAppointment(@PathVariable("id") Integer id) throws applicantException{
		return appSer.getAllApointment(id);
	}
}
