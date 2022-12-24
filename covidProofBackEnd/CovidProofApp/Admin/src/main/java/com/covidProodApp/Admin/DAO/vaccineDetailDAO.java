package com.covidProodApp.Admin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covidProodApp.Admin.Beans.vaccineDetails;

@Repository
public interface vaccineDetailDAO extends JpaRepository<vaccineDetails, Integer> {

}
