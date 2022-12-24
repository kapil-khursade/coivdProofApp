package com.covidProofApp.loginAndRegistration.Beans;

import java.sql.Date;
import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class adminIdCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@Column(unique = true)
	private String mobileno;
	@Column(unique = true)
	private String password;
}
