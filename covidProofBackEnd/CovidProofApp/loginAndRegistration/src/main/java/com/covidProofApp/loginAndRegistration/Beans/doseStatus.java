package com.covidProofApp.loginAndRegistration.Beans;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.covidProofApp.loginAndRegistration.Beans.applicantIdCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class doseStatus {

	@Id
	private Integer applicantId;
	private boolean dose1;
	private boolean dose2;
	private LocalDate dose1Date;
	private LocalDate dose2Date;

}
