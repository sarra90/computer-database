package com.excilys.computer_database.dtos;

import java.time.LocalDate;

public class ComputerDto {

	private Long id;

	private String name;

	private LocalDate introduced;

	private LocalDate disconstinued;
	
	private Long idCompany;

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

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDisconstinued() {
		return disconstinued;
	}

	public void setDisconstinued(LocalDate disconstinued) {
		this.disconstinued = disconstinued;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
	
	
}
