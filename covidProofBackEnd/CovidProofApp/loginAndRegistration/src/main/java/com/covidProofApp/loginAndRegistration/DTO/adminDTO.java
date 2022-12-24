package com.covidProofApp.loginAndRegistration.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class adminDTO {

	private String mobileno;
	private String password;
	private String newPassword;
}
