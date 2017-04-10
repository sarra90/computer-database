package com.excilys.model;

import java.util.Date;

import com.excilys.exceptions.DiscontinuedDateException;

public class Computer {

	String name;

	Date introduced;

	Date disconstinued;

	Company manufacturer;

	public Computer(String name) {
		this.name = name;
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
		}
		else throw new DiscontinuedDateException();
	}

}
