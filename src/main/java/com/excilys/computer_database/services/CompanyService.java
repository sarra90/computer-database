package com.excilys.computer_database.services;

import java.util.List;

import com.excilys.computer_database.models.Company;

public interface CompanyService {

		
		/**
		 * 
		 * @return
		 */
		public List<Company> findAll();

		/**
		 * 
		 * @param id
		 * @return
		 */
		public  Company findById(Long id);
		
		/**
		 * 
		 * @param obj
		 * @return
		 */
		public  Company create(Company obj);
		
}
