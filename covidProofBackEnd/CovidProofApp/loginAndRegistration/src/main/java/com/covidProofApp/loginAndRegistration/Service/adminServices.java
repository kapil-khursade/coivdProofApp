package com.covidProofApp.loginAndRegistration.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.covidProofApp.loginAndRegistration.Beans.adminIdCard;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.DTO.adminDTO;
import com.covidProofApp.loginAndRegistration.DTO.output;
import com.covidProofApp.loginAndRegistration.Exeptions.adminExceptions;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;

public interface adminServices {

	public ResponseEntity<adminIdCard> registerAdmin(adminIdCard adIdCard) throws adminExceptions;
	public ResponseEntity<adminIdCard> loginAdmin(adminDTO adminLog) throws adminExceptions;
	public ResponseEntity<output> deleteAdmin(Integer id) throws adminExceptions;
	public ResponseEntity<output> updatePassAdmin(adminDTO adminLog) throws adminExceptions;
	public ResponseEntity<List<applicantIdCard>> getAllApplicant() throws applicantException;
}
