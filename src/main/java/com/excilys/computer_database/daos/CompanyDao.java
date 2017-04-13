package com.excilys.computer_database.daos;

import java.util.List;

import com.excilys.computer_database.models.Company;

public interface CompanyDao {

	/**
	 * find all company
	 * 
	 * @return
	 */
	public List<Company> findAll();

	/**
	 * find company by id
	 * 
	 * @param id
	 * @return company
	 */
	public Company findById(Long id);

	/**
	 * create company
	 * 
	 * @param company
	 * @return company
	 */
	public Company create(Company company);

}
