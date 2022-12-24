package com.covidProodApp.Admin.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidProodApp.Admin.Beans.centerInventory;
import com.covidProodApp.Admin.Beans.vaccineCenter;

@Repository
public interface centerInventoryDAO extends JpaRepository<centerInventory, Integer>{

//	public List<centerInventory> findByVaccinecenter(vaccineCenter center);
}
