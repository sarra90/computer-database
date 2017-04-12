package com.excilys.computerdatabase.models;

import java.util.Date;

import com.excilys.computerdatabase.exceptions.DiscontinuedDateException;

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

	public Computer(Builder computerBuilder) {

		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.disconstinued = computerBuilder.disconstinued;
		this.manufacturer = computerBuilder.manufacturer;
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

	public static class Builder {

		
		private String name;

		private Date introduced;

		private Date disconstinued;

		private Company manufacturer;

		public Builder(String name) {
			this.name = name;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder introduced(Date introduced) {
			this.introduced = introduced;
			return this;
		}

		
		public Builder disconstinued(Date disconstinued) {
			this.disconstinued = disconstinued;
			return this;
		}

		public Builder manufacturer(Company manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}
		
		public Computer build(){
			return new Computer(this);
		}
		
	}
}
