package com.covidProodApp.Applicant.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidProodApp.Applicant.Beans.appointment;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;

@Repository
public interface appointmentDAO extends JpaRepository<appointment, Integer>{

	public appointment findByApplicant(applicantIdCard idcard);
}
