package com.excilys.mapper;

import com.excilys.dto.CompanyDto;
import com.excilys.dto.CompanyDto.Builder;
import com.excilys.model.Company;

public class MapperCompany {
    
    
    public Company convertToCompany(CompanyDto companyDto) {

        return new Company.Builder().id(companyDto.getId()).name(companyDto.getName()).build();
        
       
    }

    public CompanyDto convertToCompanyDTO(Company company) {
        
        CompanyDto companyDto =new Builder().id(company.getId()).name(company.getName()).build();
        
        return companyDto;

    }

}
