package com.covidProodApp.Applicant.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class applicantOutput {

	private String message;
	private LocalDateTime timestamp;
}
