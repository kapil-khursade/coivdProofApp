package com.covidProodApp.Admin.Services;

import java.util.ArrayList;
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
		List<centerInventory> inventList = new ArrayList<>();
		center.setInventory(inventList);
		vaccineCenter cen = vcdao.save(center);
		if(cen==null)throw new centerException("Cant Save The Center");
		
		return new ResponseEntity<adminoutput>(new adminoutput("Center Added"), HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<List<vaccineCenter>> getAllVaccCenter() throws centerException {
		// TODO Auto-generated method stub
		
		List<vaccineCenter> centerList = vcdao.findAll();
		
		if(centerList.size()==0)throw new centerException("No Center Added");
		
		for(vaccineCenter vc:centerList) {
			int totaldose = 0;
			for(centerInventory ci:vc.getInventory()) {
				totaldose+=ci.getQuantity();
			}
			vc.setTotalDoses(totaldose);
		}
		
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
	public ResponseEntity<adminoutput> addCenterInventory(centerInventory inve, Integer id) throws centerException {
		// TODO Auto-generated method stub
		vaccineCenter center = vcdao.findById(id).get();
		
		List<centerInventory> inveList = center.getInventory();
		inveList.add(inve);
		
		int totaldose = 0;
		for(centerInventory ci:inveList) {
			totaldose+=ci.getQuantity();
		}
		
		center.setInventory(inveList);
		center.setTotalDoses(totaldose);
		vcdao.save(center);
		inve.setCenter(center);
		cidao.save(inve);
		
		return new ResponseEntity<adminoutput>(new adminoutput("Invenotry Added For "+center.getCenterName()), HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<List<centerInventory>> getCenterInventory(Integer id) throws centerException {
		// TODO Auto-generated method stub
		
		List<centerInventory> inveList = vcdao.findById(id).get().getInventory();
		
		return new  ResponseEntity<List<centerInventory>>(inveList, HttpStatus.ACCEPTED);
	}


	@Override
	public ResponseEntity<adminoutput> deleteInventory(Integer id) throws centerException {
		// TODO Auto-generated method stub
	    
		Optional<centerInventory> inve = cidao.findById(id);
		
		if(inve.isPresent()) {
			cidao.delete(inve.get());
		}else {
			throw new centerException("No Center Inventory Exist With Id "+id);
		}
		
		return new ResponseEntity<adminoutput>(new adminoutput("Invenotry Deleted"), HttpStatus.ACCEPTED);
	}

}
