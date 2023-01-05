package com.covidProodApp.Admin.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class vaccineCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String centerName;
	private String city;
	private String state;
	private String pincode;
	private int totalDoses;
	
	@OneToMany(mappedBy = "center", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("center")
	private List<centerInventory> inventory = new ArrayList<>();
}
