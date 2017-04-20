package com.excilys.dtos;

import java.time.LocalDate;

public class ComputerDto {

	private Long id;

	private String name;

	private String introduced;

	private String disconstinued;
	
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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDisconstinued() {
		return disconstinued;
	}

	public void setDisconstinued(String disconstinued) {
		this.disconstinued = disconstinued;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	private Long idCompany;

	public static class Builder{
		
		private Long id;

		private String name;

		private String introduced;

		private String disconstinued;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder introduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder disconstinued(String disconstinued) {
			this.disconstinued = disconstinued;
			return this;
		}
		public ComputerDto build(){
			return new ComputerDto();
		}
		
	}
}