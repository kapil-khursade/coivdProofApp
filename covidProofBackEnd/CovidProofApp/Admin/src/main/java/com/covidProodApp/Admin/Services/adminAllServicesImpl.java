package com.covidProodApp.Admin.Services;

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
import com.covidProodApp.Admin.DTO.adminoutput;
import com.covidProodApp.Admin.Exceptions.centerException;
import com.covidProodApp.Admin.Exceptions.vaccineDetailException;

@Service
public class adminAllServicesImpl implements adminAllServices {

	@Autowired
	private vaccineDetailDAO vddao;
	@Autowired
	private vaccineCenterDAO vcdao;
	@Autowired
	private centerInventoryDAO cidao;
	
	@Override
	public ResponseEntity<adminoutput> addVaccine(vaccineDetails vaccine) throws vaccineDetailException {
		// TODO Auto-generated method stub
		
		vaccineDetails addedVaccine = vddao.save(vaccine);
		if(addedVaccine==null)throw new vaccineDetailException("Cant Add The Vaccine");
		
		return new ResponseEntity<adminoutput>(new adminoutput("New Vaccine Added with Name "+addedVaccine.getVaccineName()), HttpStatus.ACCEPTED);
	}
	

	@Override
	public ResponseEntity<List<vaccineDetails>> getAllVaccies() throws vaccineDetailException {
		// TODO Auto-generated method stub
		List<vaccineDetails> vaccineList = vddao.findAll();
		
		if(vaccineList.size()==0)throw new vaccineDetailException("No Vacacine Data Exist");
		
		return new ResponseEntity<List<vaccineDetails>>(vaccineList, HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<adminoutput> deleteVaccine(Integer id) throws vaccineDetailException {
		// TODO Auto-generated method stub
		
		Optional<vaccineDetails> vaccine = vddao.findById(id);
		
		if(!vaccine.isPresent())throw new vaccineDetailException("Vaccine Not Exist With Id "+id);
		else vddao.delete(vaccine.get());
		
		return new ResponseEntity<adminoutput>(new adminoutput(vaccine.get().getVaccineName()+" Is Deleted"), HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<adminoutput> addVaccineCenter(vaccineCenter center) throws centerException {
		// TODO Auto-generated method stub
		
		vaccineCenter cen = vcdao.save(center);
		if(cen==null)throw new centerException("Cant Save The Center");
		
		return new ResponseEntity<adminoutput>(new adminoutput("Center Added"), HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<List<vaccineCenter>> getAllVaccCenter() throws centerException {
		// TODO Auto-generated method stub
		
		List<vaccineCenter> centerList = vcdao.findAll();
		
		if(centerList.size()==0)throw new centerException("No Center Added");
		
		return new ResponseEntity<List<vaccineCenter>>(centerList, HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<adminoutput> deleteVaccineCenter(Integer id) throws centerException {
		// TODO Auto-generated method stub
		Optional<vaccineCenter> cent = vcdao.findById(id);
		if(!cent.isPresent())throw new centerException("No Center Exist With Id "+id);
		else {
			vcdao.delete(cent.get());
		}
		return new ResponseEntity<adminoutput>(new adminoutput("Center Deleted With Id "+id), HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<adminoutput> addCenterInventory(vaccineCenter center) throws centerException {
		// TODO Auto-generated method stub
		vaccineCenter vcent = vcdao.save(center);
//		centerInventory inven = center.getInventory().get(center.getInventory().size()-1);
//		cidao.save(inven);
		return new ResponseEntity<adminoutput>(new adminoutput("Invenotry Added For "), HttpStatus.ACCEPTED);
	}

}