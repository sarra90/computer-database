package com.excilys.service;

import java.util.List;

import com.excilys.model.Company;

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
