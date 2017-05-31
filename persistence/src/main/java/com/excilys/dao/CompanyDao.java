package com.excilys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;

/**
 * interface represente a company Dao.
 * 
 * @author excilys
 *
 */
@Transactional(propagation=Propagation.MANDATORY)
@Repository
public interface CompanyDao extends JpaRepository<Company, Long>{

}
