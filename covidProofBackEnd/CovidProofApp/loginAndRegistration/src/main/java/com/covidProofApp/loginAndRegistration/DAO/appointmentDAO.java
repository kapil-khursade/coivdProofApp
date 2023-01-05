package com.covidProofApp.loginAndRegistration.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;
import com.covidProofApp.loginAndRegistration.Beans.appointment;

public interface appointmentDAO extends JpaRepository<appointment, Integer> {

	public List<appointment> findByIdcard(applicantIdCard app);
}
