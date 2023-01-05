package com.covidProofApp.loginAndRegistration.Beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.covidProodApp.Admin.Beans.vaccineCenter;
import com.covidProodApp.Admin.Beans.vaccineDetails;

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
	@OneToOne
	private applicantIdCard idcard;
	private LocalDate bookingDate;
	@OneToOne
	private vaccineDetails vaccine;
	@OneToOne
	private vaccineCenter center;
	private int slot;
	private int doseNo;
	
}
