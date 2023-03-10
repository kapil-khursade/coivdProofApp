package com.covidProofApp.loginAndRegistration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.covidProofApp.loginAndRegistration.Beans.adminIdCard;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.DAO.adminDAO;
import com.covidProofApp.loginAndRegistration.DAO.applicantDao;
import com.covidProofApp.loginAndRegistration.DTO.adminDTO;
import com.covidProofApp.loginAndRegistration.DTO.output;
import com.covidProofApp.loginAndRegistration.Exeptions.adminExceptions;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;

@Service
public class adminServicesImpl implements adminServices  {

	@Autowired
	private adminDAO adao;
	@Autowired
	private applicantDao apdao;
	
	@Override
	public ResponseEntity<adminIdCard> registerAdmin(adminIdCard adIdCard) throws adminExceptions {
		// TODO Auto-generated method stub
		adminIdCard idcard = adao.save(adIdCard);
		if(idcard==null)throw new adminExceptions("Cant Save The Admin.");
		return new ResponseEntity<adminIdCard>(idcard, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<adminIdCard> loginAdmin(adminDTO adminLog) throws adminExceptions {
		// TODO Auto-generated method stub
		 adminIdCard adminidcard = adao.findByMobileno(adminLog.getMobileno());
		if(adminidcard==null)throw new adminExceptions("Account Not Exist");
		if(!adminLog.getPassword().equals(adminidcard.getPassword()))throw new adminExceptions("Wrong Password");
		return new ResponseEntity<adminIdCard>(adminidcard, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<output> deleteAdmin(Integer id) throws adminExceptions {
		// TODO Auto-generated method stub
		Optional<adminIdCard> adminIdCard = adao.findById(id);
		if(adminIdCard.isEmpty()) {
			throw new adminExceptions("No admin Account Exist With ID "+ id);
		}else {
			adao.delete(adminIdCard.get());
		}
		return new ResponseEntity<output>(new output("Account Deleted With ID "+id), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<output> updatePassAdmin(adminDTO adminLog) throws adminExceptions {
		// TODO Auto-generated method stub
		 adminIdCard adminidcard = adao.findByMobileno(adminLog.getMobileno());
		if(adminidcard==null)throw new adminExceptions("Account Not Exist");
		if(!adminLog.getPassword().equals(adminidcard.getPassword()))throw new adminExceptions("Wrong Password");
		
		if(adminLog.getNewPassword()!=null) {
		adminidcard.setPassword(adminLog.getNewPassword());
		adminIdCard newAdminIDCard = adao.save(adminidcard);
		}else {
			throw new adminExceptions("New Password Is Not Valid");
		}
		
		return new ResponseEntity<output>(new output("Password Updated"), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<applicantIdCard>> getAllApplicant() throws applicantException {
		// TODO Auto-generated method stub
		
		List<applicantIdCard> listOfApplicant = apdao.findAll();
		
		if(listOfApplicant.size()==0)throw new applicantException("No Applicant is there");
		
		return new ResponseEntity<List<applicantIdCard>>(listOfApplicant, HttpStatus.ACCEPTED);
	}

}

