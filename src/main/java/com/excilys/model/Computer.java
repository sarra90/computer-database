package com.excilys.model;

import java.util.Date;

import com.excilys.builder.Computerbuilder;
import com.excilys.exceptions.DiscontinuedDateException;

/**
 * class represent a Company
 * 
 * @author sarra
 * @version 10/04/17
 */

public class Computer {

	private Long id;

	private String name;

	private Date introduced;

	private Date disconstinued;

	private Company manufacturer;

	public Computer(Computerbuilder builder) {

		this.name = builder.name;
		this.introduced = builder.introduced;
		this.disconstinued = builder.disconstinued;
		this.manufacturer = builder.manufacturer;
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDisconstinued() {
		return disconstinued;
	}

	public void setDisconstinued(Date disconstinued) throws DiscontinuedDateException {

		if (disconstinued.after(introduced)) {

			this.disconstinued = disconstinued;
		} else
			throw new DiscontinuedDateException();
	}

	public Company getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Computer [name=" + name + ", introduced=" + introduced + ", disconstinued=" + disconstinued
				+ ", manufacturer=" + manufacturer + "]";
	}

}
