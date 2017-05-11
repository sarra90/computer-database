package com.excilys.dto;

public class ComputerDto {

    private long id;

    private String name;

    private String introduced;

    private String disconstinued;

    private Long idCompany;

    private ComputerDto(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.disconstinued = builder.disconstinued;
        this.idCompany = builder.idCompany;
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

        public ComputerDto build() {
            return new ComputerDto(this);
        }

    }

}