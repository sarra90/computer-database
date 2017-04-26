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