package com.excilys.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * class represent a Computer.
 * 
 * @author sarra
 * @version 10/04/17
 */

@Entity
public class Computer {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private LocalDate introduced;

    private LocalDate disconstinued;

    private Company manufacturer;

    public Computer(Builder computerBuilder) {

        this.id = computerBuilder.id;
        this.name = computerBuilder.name;
        this.introduced = computerBuilder.introduced;
        this.disconstinued = computerBuilder.disconstinued;
        this.manufacturer = computerBuilder.manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Company getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Company manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((disconstinued == null) ? 0 : disconstinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if (obj != null) {

            if (obj instanceof Computer) {

                Computer computer = (Computer) obj;

                if (this.getId().equals(computer.getId())) {

                    result = true;

                } else if ((this.getName().equals(computer.getName()))
                        && (this.getIntroduced().equals(computer.getIntroduced()))
                        && (this.getDisconstinued().equals(computer.getDisconstinued()))
                        && (this.getManufacturer().equals(computer.getManufacturer()))) {

                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", disconstinued="
                + disconstinued + ", manufacturer=" + manufacturer + "]";
    }

    public static class Builder {

        private long id;

        private String name;

        private LocalDate introduced;

        private LocalDate disconstinued;

        private Company manufacturer;

        public Builder(String name) {
            this.name = name;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder introduced(LocalDate introduced) {
            this.introduced = introduced;
            return this;
        }

        public Builder disconstinued(LocalDate disconstinued) {
            this.disconstinued = disconstinued;
            return this;
        }

        public Builder manufacturer(Company manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }

    }
}
