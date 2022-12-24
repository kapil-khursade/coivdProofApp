package com.covidProofApp.loginAndRegistration.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class applicantDTO {

	private String mobileno;
	private Date dob;
}
