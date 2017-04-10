package com.excilys.patterns;

import java.util.Date;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class Computerbuilder {

	public String name;

	public Date introduced;

	public Date disconstinued;

	public Company manufacturer;

	public Computerbuilder(String name) {
		this.name = name;
	}

	public Computerbuilder setName(String name) {
		this.name = name;
		return this;
	}

	public Computerbuilder setIntroduced(Date introduced) {
		this.introduced = introduced;
		return this;
	}

	
	public Computerbuilder setDisconstinued(Date disconstinued) {
		this.disconstinued = disconstinued;
		return this;
	}

	public Computerbuilder setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
		return this;
	}
	
	public Computer build(){
		return new Computer(this);
	}
	
}
