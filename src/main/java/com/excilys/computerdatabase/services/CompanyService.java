package com.excilys.computerdatabase.services;

import java.util.List;

import com.excilys.computerdatabase.models.Company;

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
		
<<<<<<< HEAD:src/main/java/com/excilys/service/CompanyService.java
		public List<String> find();
=======
>>>>>>> origin/develop:src/main/java/com/excilys/computerdatabase/services/CompanyService.java
}
