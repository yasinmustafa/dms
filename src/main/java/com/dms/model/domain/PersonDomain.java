package com.dms.model.domain;


public class PersonDomain extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	 private Integer perId;
     private String firstName;
     private String surname;
     private Integer companyInd;
     private String companyName;
     private Integer sortOrder;

    public PersonDomain() {
    }

    public Integer getPerId() {
        return this.perId;
    }
    
    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getCompanyInd() {
        return this.companyInd;
    }
    
    public void setCompanyInd(Integer companyInd) {
        this.companyInd = companyInd;
    }

    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    public Integer getSortOrder() {
        return this.sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}


