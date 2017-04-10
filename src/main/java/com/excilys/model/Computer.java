package com.excilys.model;

import java.util.Date;

import com.excilys.exceptions.DiscontinuedDateException;
import com.excilys.patterns.Computerbuilder;

public class Computer {

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

}
