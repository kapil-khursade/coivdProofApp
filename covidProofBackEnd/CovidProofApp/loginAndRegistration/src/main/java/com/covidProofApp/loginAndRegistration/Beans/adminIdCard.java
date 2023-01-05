package com.covidProofApp.loginAndRegistration.Beans;

import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class adminIdCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NonNull
	@Size(min = 3, max = 25, message = "Name Must Be Between 3 and 25 Characters")
	private String name;
	@NonNull
	@Size(min = 10, max = 10, message = "Phone No Must Be of 10 Digits only")
	@Column(unique = true)
	private String mobileno;
	@NonNull
	@Size(min = 6, max = 10, message = "Password Must be between 6 to 10 Characters Only")
	@Column(unique = true)
	private String password;
}
