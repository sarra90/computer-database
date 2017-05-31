package com.excilys.dto;

import org.springframework.stereotype.Component;

@Component
public class CompanyDto {

    private long id;
    private String name;

    public CompanyDto(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
    }

    public CompanyDto() {
        super();
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

        public CompanyDto build() {
            return new CompanyDto(this);
        }
    }

   
}
