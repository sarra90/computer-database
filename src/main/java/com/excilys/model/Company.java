package com.excilys.model;

/**
 * class represent a Company
 * 
 * @author sarra
 * @version 10/04/17
 */
public class Company {

	private Long id;
	private String name;

	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Company(Builder CompanyBuilder) {

		this.id=CompanyBuilder.id;
		this.name = CompanyBuilder.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		boolean result = false;
	
		if (obj!=null){
			
			if(obj instanceof Company){
				
				Company company = (Company)obj;
				
				if(this.getId().equals(company.getId())){
					
					result = true;
				}
				else if(this.getName().equals(company.getName())){
					
				}
			}
		}
		return result ;
	}

	public static class Builder{
		
		private Long id ;
		private String name;
		
		public Builder id(Long id){
			this.id=id;
			return this;
		}
		public Builder name(String name){
			this.name=name;
			return this;
		}
		public Company build(){
			return new Company(this);
		}
	}

}