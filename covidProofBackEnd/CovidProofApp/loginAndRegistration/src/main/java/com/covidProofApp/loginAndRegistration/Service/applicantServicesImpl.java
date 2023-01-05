package com.covidProofApp.loginAndRegistration.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.covidProodApp.Admin.Beans.centerInventory;
import com.covidProodApp.Admin.Beans.vaccineCenter;
import com.covidProodApp.Admin.Beans.vaccineDetails;
import com.covidProodApp.Admin.DAO.centerInventoryDAO;
import com.covidProodApp.Admin.DAO.vaccineCenterDAO;
import com.covidProodApp.Admin.DAO.vaccineDetailDAO;
import com.covidProodApp.Admin.Services.adminAllServices;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.Beans.appointment;
import com.covidProofApp.loginAndRegistration.Beans.doseStatus;
import com.covidProofApp.loginAndRegistration.DAO.applicantDao;
import com.covidProofApp.loginAndRegistration.DAO.appointmentDAO;
import com.covidProofApp.loginAndRegistration.DAO.doseStatusDAO;
import com.covidProofApp.loginAndRegistration.DTO.applicantDTO;
import com.covidProofApp.loginAndRegistration.DTO.output;
import com.covidProofApp.loginAndRegistration.Exeptions.applicantException;

@Service
public class applicantServicesImpl implements applicantsServices {

	@Autowired
	private applicantDao apdao;

	@Autowired
	private doseStatusDAO dsdao;
	
	
	@Override
	public ResponseEntity<output>  registerApplicant(applicantIdCard idCard) throws applicantException {
		// TODO Auto-generated method stub
		applicantIdCard id = apdao.save(idCard);
		
		doseStatus ds = new doseStatus();
		ds.setApplicantId(id.getId());
		
		dsdao.save(ds);
				
		return new ResponseEntity<output>(new output("applicnat added"), HttpStatus.ACCEPTED);
		
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
			Optional<doseStatus> ds = dsdao.findById(id);
			dsdao.delete(ds.get());
		}
		return new ResponseEntity<String>("Account Deleted Successfull With ID "+id, HttpStatus.ACCEPTED);
	}
	

	@Override
	public ResponseEntity<doseStatus> getAppDoseStatus(int id) throws applicantException {
		// TODO Auto-generated method stub
	    Optional<doseStatus> doseStatus = dsdao.findById(id);
		
		if(!doseStatus.isPresent())throw new applicantException("Applicant Doesnto Exist with ID "+id);
		
		return new ResponseEntity<doseStatus>(doseStatus.get(), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<output> updateDoseStatus(int id) throws applicantException {
		// TODO Auto-generated method stub
		Optional<doseStatus> doseStatus = dsdao.findById(id);
		
		if(doseStatus.isEmpty())throw new applicantException("Applicant Doesnto Exist with ID "+id);
		
		doseStatus ds = doseStatus.get();
		
		String mes = "";
		
		if(!ds.isDose1()) {
			ds.setDose1(true);
			ds.setDose1Date(LocalDate.now());	
			mes = "Dose 1 Updated";
			
			dsdao.save(ds);
		}else if(!ds.isDose2()) {
			Duration diff = Duration.between(ds.getDose1Date().atStartOfDay(), LocalDate.now().atStartOfDay());
			long diffDays = diff.toDays();
			if(diffDays<30) {
				throw new applicantException("Cant Take 2nd Dose With in 30 Days");
			}
			
			ds.setDose2(true);
			ds.setDose2Date(LocalDate.now());
			mes = "Dose 2 Updated";
			dsdao.save(ds);
		}else {
			throw new applicantException("Required Two Doses Is Taken");
		}
		return new ResponseEntity<output>(new output(mes), HttpStatus.ACCEPTED);
	}

	@Autowired
	private appointmentDAO appointdao;
	@Autowired
	private vaccineCenterDAO vcdao;
	@Autowired
	private centerInventoryDAO cidao;
	@Autowired
	private vaccineDetailDAO vddao;
	
	@Override
	public ResponseEntity<appointment> addAppointmenr(appointment app) throws applicantException {
		// TODO Auto-generated method stub
		
		vaccineCenter center = vcdao.findById(app.getCenter().getId()).get();
		vaccineDetails vaccine = vddao.findById(app.getVaccine().getId()).get();
		List<centerInventory> inveList = center.getInventory();
		
		for(centerInventory il:inveList) {
			if(il.getVaccine().equals(vaccine)&&il.getQuantity()>0) {
				il.setQuantity(il.getQuantity()-1);
				center.setTotalDoses(center.getTotalDoses()-1);
				vcdao.save(center);
				cidao.save(il);
				break;
			}
		}
		 
		return new ResponseEntity<appointment>(appointdao.save(app), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<output> deleteAppointment(Integer id) throws applicantException {
		// TODO Auto-generated method stub
		Optional<appointment> ap  =  appointdao.findById(id);
		

		if(ap.isEmpty())throw new applicantException("No Appointment Exist With Id "+id);
		
		appointdao.delete(ap.get());
		
		return new ResponseEntity<output>(new output("Appointment Cancel"), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<appointment>> getAllApointment(Integer appId) throws applicantException {
		// TODO Auto-generated method stub
		
		Optional<applicantIdCard> apid = apdao.findById(appId);
		
		if(appId==null)throw new applicantException("No Applicant Doesnt Exist");
		
		List<appointment> appointList = appointdao.findByIdcard(apid.get());
		
		if(appointList.size()==0)throw new applicantException("No Appointment Available");
		
		return new ResponseEntity<List<appointment>>(appointList, HttpStatus.ACCEPTED);
	}




	

}
