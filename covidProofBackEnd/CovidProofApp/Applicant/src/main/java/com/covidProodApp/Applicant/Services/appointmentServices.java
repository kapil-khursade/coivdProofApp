package com.covidProodApp.Applicant.Services;

import org.springframework.http.ResponseEntity;

import com.covidProodApp.Applicant.Beans.appointment;
import com.covidProodApp.Applicant.Beans.doseStatus;
import com.covidProodApp.Applicant.DTO.applicantOutput;
import com.covidProodApp.Applicant.Exceptions.applicantExceptions;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;

public interface appointmentServices {

	public ResponseEntity<applicantOutput> setDoseStatus(doseStatus dosestat) throws applicantExceptions;
	public ResponseEntity<doseStatus> getDoseStatus(applicantIdCard idcard) throws applicantExceptions;
	public ResponseEntity<applicantOutput> addAppointment(appointment app) throws applicantExceptions;
	public ResponseEntity<appointment> getAppointment(applicantIdCard applicant) throws applicantExceptions;
	public ResponseEntity<applicantOutput> deleteAppointment(Integer id) throws applicantExceptions;
	
}
