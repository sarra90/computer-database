package com.excilys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.model.Company;

/**
 * interface represente a company Dao.
 * 
 * @author excilys
 *
 */
public interface CompanyDao extends JpaRepository<Company, Long>{

}
