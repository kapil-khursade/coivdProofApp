package com.covidProofApp.loginAndRegistration.Beans;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class applicantIdCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Date dob;
	private String gender;
	@Column(unique = true)
	private String mobileno;
	private String address;
	private String city;
	private int pincode;
	
	@OneToOne
	@JsonIgnore
	private applicantAdhar adhar;
	@OneToOne
	@JsonIgnore
	private applicantPan pan;
}
