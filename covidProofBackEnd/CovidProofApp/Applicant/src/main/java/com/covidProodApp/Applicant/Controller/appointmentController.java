package com.covidProodApp.Applicant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidProodApp.Applicant.Beans.doseStatus;
import com.covidProodApp.Applicant.DTO.applicantOutput;
import com.covidProodApp.Applicant.Exceptions.applicantExceptions;
import com.covidProodApp.Applicant.Services.appointmentServices;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;


@RestController
@RequestMapping("/appointment")
public class appointmentController {

	@Autowired
	private appointmentServices apserv;
	
	@PostMapping("/setDoseStatus")
	public ResponseEntity<applicantOutput> setDoseStatus(@RequestBody doseStatus dostat) throws applicantExceptions{
		return apserv.setDoseStatus(dostat);
	}
	
}
