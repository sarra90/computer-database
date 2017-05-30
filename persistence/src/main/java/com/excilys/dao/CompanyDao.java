package com.excilys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.Company;

/**
 * interface represente a company Dao.
 * 
 * @author excilys
 *
 */
@Repository
public interface CompanyDao extends JpaRepository<Company, Long>{

}
