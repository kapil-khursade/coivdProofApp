package com.covidProofApp.loginAndRegistration.Service;

import org.springframework.http.ResponseEntity;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.DTO.applicantDTO;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;

public interface applicantsServices {

	public ResponseEntity<applicantIdCard>  registerApplicant(applicantIdCard idCard) throws applicantException;
	public ResponseEntity<applicantIdCard>  loginApplicant(applicantDTO appLogin) throws applicantException;
	public ResponseEntity<String> deleteApplicant(Integer id) throws applicantException;
	
}
