package com.covidProodApp.Admin.Beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class vaccineCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String centerName;
	private String city;
	private String state;
	private String pincode;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<centerInventory> inventory;
}
