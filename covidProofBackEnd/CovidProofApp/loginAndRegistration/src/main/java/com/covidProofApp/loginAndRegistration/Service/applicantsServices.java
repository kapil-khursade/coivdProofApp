package com.covidProofApp.loginAndRegistration.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.Beans.appointment;
import com.covidProofApp.loginAndRegistration.Beans.doseStatus;
import com.covidProofApp.loginAndRegistration.DTO.applicantDTO;
import com.covidProofApp.loginAndRegistration.DTO.output;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;

public interface applicantsServices {

	public ResponseEntity<output>  registerApplicant(applicantIdCard idCard) throws applicantException;
	public ResponseEntity<applicantIdCard>  loginApplicant(applicantDTO appLogin) throws applicantException;
	public ResponseEntity<String> deleteApplicant(Integer id) throws applicantException;
	
	public ResponseEntity<doseStatus> getAppDoseStatus(int id) throws applicantException;
	public ResponseEntity<output> updateDoseStatus(int id) throws applicantException;
	
	public ResponseEntity<appointment> addAppointmenr(appointment app) throws applicantException;
	public ResponseEntity<output> deleteAppointment(Integer id)throws applicantException;
	public ResponseEntity<List<appointment>> getAllApointment(Integer appId) throws applicantException;
}
