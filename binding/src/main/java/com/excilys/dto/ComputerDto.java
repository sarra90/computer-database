package com.excilys.dto;

import javax.validation.constraints.NotNull;

import com.excilys.validations.Date;

public class ComputerDto {

    private long id;

    @NotNull
    private String name;

    @Date
    private String introduced;

    @Date
    private String disconstinued;

    private long idCompany;
    
    private String nameCompany;

    private ComputerDto(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.disconstinued = builder.disconstinued;
        this.idCompany = builder.idCompany;
        this.nameCompany=builder.nameCompany;
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

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDisconstinued() {
        return disconstinued;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public void setDisconstinued(String disconstinued) {
        this.disconstinued = disconstinued;
    }
    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }
    @Override
    public String toString() {
        return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced + ", disconstinued="
                + disconstinued + ", id_company=" + idCompany + "]";
    }

    public static class Builder {

        private long id;

        private String name;

        private String introduced;

        private String disconstinued;

        private long idCompany;
        
        private String nameCompany;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

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

        public Builder idCompany(long idCompany) {
            this.idCompany = idCompany;
            return this;
        }
        public Builder nameCompany(String nameCompany) {
            this.nameCompany = nameCompany;
            return this;
        }


        public ComputerDto build() {
            return new ComputerDto(this);
        }

    }

   

}