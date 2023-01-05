package com.covidProofApp.loginAndRegistration.Beans;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class applicantIdCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NonNull
	@Size(min=3, max=25, message = "Name Must Be Between 3 and 25 Characters")
	private String name;
	@NotNull
	@Past(message = "Invalid Date Of Birth")
	private Date dob;
	@NotNull
	private String gender;
	@NotNull
	@Column(unique = true)
	@Size(min=10, max=10, message = "Phone Number Must Be 10 Digits only")
	private String mobileno;
	@NotNull
	private String address;
	@NotNull
	private String city;
	@NotNull
	private int pincode;
	@NotNull
	@Column(unique = true)
	private long adharNo;
	@NotNull
	@Size(min=6, max=6, message = "Pan Number Must Be 6 Deigits Only")
	@Column(unique = true)
	private String panNo;
	
}
