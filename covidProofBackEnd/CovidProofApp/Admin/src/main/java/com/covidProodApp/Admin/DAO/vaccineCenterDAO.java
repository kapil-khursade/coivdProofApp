package com.covidProodApp.Admin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidProodApp.Admin.Beans.vaccineCenter;

public interface vaccineCenterDAO extends JpaRepository<vaccineCenter, Integer> {

}
