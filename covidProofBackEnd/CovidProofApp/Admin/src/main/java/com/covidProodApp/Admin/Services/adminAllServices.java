package com.covidProodApp.Admin.Services;

import org.springframework.http.ResponseEntity;

import com.covidProodApp.Admin.Beans.centerInventory;
import com.covidProodApp.Admin.Beans.vaccineCenter;
import com.covidProodApp.Admin.Beans.vaccineDetails;
import com.covidProodApp.Admin.DTO.adminoutput;
import com.covidProodApp.Admin.Exceptions.centerException;
import com.covidProodApp.Admin.Exceptions.vaccineDetailException;
import java.util.*;

public interface adminAllServices {

	public ResponseEntity<adminoutput> addVaccine(vaccineDetails vaccine) throws vaccineDetailException;
	public ResponseEntity<List<vaccineDetails>> getAllVaccies() throws vaccineDetailException;
	public ResponseEntity<adminoutput> deleteVaccine(Integer id) throws vaccineDetailException;
	public ResponseEntity<adminoutput> addVaccineCenter(vaccineCenter center) throws centerException;
	public ResponseEntity<List<vaccineCenter>> getAllVaccCenter() throws centerException;
	public ResponseEntity<adminoutput> deleteVaccineCenter(Integer id) throws centerException;
	public ResponseEntity<adminoutput> addCenterInventory(vaccineCenter center) throws centerException;
}
