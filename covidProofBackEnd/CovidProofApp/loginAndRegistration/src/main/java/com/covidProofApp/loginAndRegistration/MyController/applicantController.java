package com.covidProofApp.loginAndRegistration.MyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.DTO.applicantDTO;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;
import com.covidProofApp.loginAndRegistration.Service.applicantsServices;

@RestController
@RequestMapping("/applicant")
public class applicantController {
	
	@Autowired
	private applicantsServices appSer;

	@PostMapping("/register")
	public ResponseEntity<applicantIdCard> registerApplicant(@RequestBody applicantIdCard idCard) throws applicantException{
		
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
}
