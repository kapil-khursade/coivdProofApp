package com.covidProofApp.loginAndRegistration.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.covidProofApp.loginAndRegistration.Beans.doseStatus;

@Repository
public interface doseStatusDAO extends JpaRepository<doseStatus, Integer> {

}
