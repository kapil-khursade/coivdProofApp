package com.covidProofApp.loginAndRegistration.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.covidProofApp.loginAndRegistration.Beans.adminIdCard;
import com.covidProofApp.loginAndRegistration.Exeptions.adminExceptions;

@Repository
public interface adminDAO extends JpaRepository<adminIdCard, Integer> {

	public adminIdCard findByMobileno(String mobileno) throws adminExceptions;

}
