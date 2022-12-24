package com.covidProofApp.loginAndRegistration.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.DAO.applicantDao;
import com.covidProofApp.loginAndRegistration.DTO.applicantDTO;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;

@Service
public class applicantServicesImpl implements applicantsServices {

	@Autowired
	private applicantDao apdao;
	
	@Override
	public ResponseEntity<applicantIdCard>  registerApplicant(applicantIdCard idCard) throws applicantException {
		// TODO Auto-generated method stub
		return new ResponseEntity<applicantIdCard>(apdao.save(idCard), HttpStatus.ACCEPTED);
		
	}

	@Override
	public ResponseEntity<applicantIdCard> loginApplicant(applicantDTO appLogin) throws applicantException {
		// TODO Auto-generated method stub
		
		applicantIdCard appidcard = apdao.findByMobileno(appLogin.getMobileno());
		
		if(appidcard==null)throw new applicantException("Account No Exist With This Number");
		if(!(appidcard.getDob().toString().equals(appLogin.getDob().toString())))throw new applicantException("Wrong Password");
		
		return new ResponseEntity<applicantIdCard>(appidcard, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> deleteApplicant(Integer id) throws applicantException {
		// TODO Auto-generated method stub
		Optional<applicantIdCard> app = apdao.findById(id);
		if(app.isEmpty())throw new applicantException("Applicant Doesnto Exist with ID "+id);
		else {
			apdao.delete(app.get());
		}
		return new ResponseEntity<String>("Account Deleted Successfull With ID "+id, HttpStatus.ACCEPTED);
	}

}
