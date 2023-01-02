package com.covidProodApp.Applicant.Beans;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.covidProodApp.Admin.Beans.vaccineCenter;
import com.covidProodApp.Admin.Beans.vaccineDetails;
import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCard")
	private applicantIdCard applicant;
	private int doseNo;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccineId")
	private vaccineDetails vaccine;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centerID")
	private vaccineCenter center;
	private LocalDate dateOfAppointment;
	private String slot;
}
