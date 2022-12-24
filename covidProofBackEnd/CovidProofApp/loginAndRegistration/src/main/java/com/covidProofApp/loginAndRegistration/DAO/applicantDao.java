package com.covidProofApp.loginAndRegistration.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;

@Repository
public interface applicantDao extends JpaRepository<applicantIdCard, Integer> {

	public applicantIdCard findByMobileno(String mobileno);
}
