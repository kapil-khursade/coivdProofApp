package com.covidProodApp.Applicant.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.covidProodApp.Applicant.Beans.appointment;
import com.covidProodApp.Applicant.Beans.doseStatus;
import com.covidProodApp.Applicant.DAO.appointmentDAO;
import com.covidProodApp.Applicant.DAO.doseStatusDAO;
import com.covidProodApp.Applicant.DTO.applicantOutput;
import com.covidProodApp.Applicant.Exceptions.applicantExceptions;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;

@Service
public class appointmentServicesImpl  implements appointmentServices {
	
	@Autowired
	private doseStatusDAO dsdao;
	@Autowired
	private appointmentDAO  apdao;
	
	@Override
	public ResponseEntity<applicantOutput> setDoseStatus(doseStatus dosestat) throws applicantExceptions {
		// TODO Auto-generated method stub
		dsdao.save(dosestat);
		return new ResponseEntity<applicantOutput>(new applicantOutput("Dose status created", LocalDateTime.now()), HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<doseStatus> getDoseStatus(applicantIdCard idcard) throws applicantExceptions {
		// TODO Auto-generated method stub
		doseStatus dosetat =  dsdao.findByApplicant(idcard);
		
		if(dosetat==null)throw new applicantExceptions("No Dose Status For Id "+idcard.getId());
		else return new ResponseEntity<doseStatus>(dosetat, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<applicantOutput> addAppointment(appointment appli) throws applicantExceptions {
		// TODO Auto-generated method stub
		apdao.save(appli);
		
		return new ResponseEntity<applicantOutput>(new applicantOutput("Your Slot Is Booked", LocalDateTime.now()), HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<applicantOutput> deleteAppointment(Integer id) throws applicantExceptions {
		// TODO Auto-generated method stub
	    Optional<appointment> appo = apdao.findById(id);
	    
	    if(appo.isPresent()) {
	    	apdao.delete(appo.get());
	    }else {
	    	throw new applicantExceptions("No Appointments With Id "+ id);
	    }
	    
		return new ResponseEntity<applicantOutput>(new applicantOutput("Appointment Deleted", LocalDateTime.now()), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<appointment> getAppointment(applicantIdCard applicant) throws applicantExceptions {
		// TODO Auto-generated method stub
		return null;
	}


}
