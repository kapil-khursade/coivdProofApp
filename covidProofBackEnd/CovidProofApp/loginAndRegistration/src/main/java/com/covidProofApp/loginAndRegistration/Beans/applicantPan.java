package com.covidProofApp.loginAndRegistration.Beans;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class applicantPan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String panNumber;
	
	@OneToOne
	@JsonIgnore
	private applicantIdCard applicant;
}
