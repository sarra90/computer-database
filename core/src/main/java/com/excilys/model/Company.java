package com.excilys.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * class represent a Company.
 * 
 * @author sarra
 * @version 10/04/17
 */
@Entity
@Table(name="company")
public class Company implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;

    public Company(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    
    public Company() {
    }


    public Company(Builder companyBuilder) {

        this.id = companyBuilder.id;
        this.name = companyBuilder.name;
    }

    public long getId() {
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

    @Override
    public String toString() {
        return "Company [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if (obj != null) {

            if (obj instanceof Company) {

                Company company = (Company) obj;

                if (this.getId() == (company.getId())) {

                    result = true;

                } else if (this.getName().equals(company.getName())) {
                    
                    result = true;
                }
            }
        }
        return result;
    }

    public static class Builder {

        private long id;
        private String name;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

}