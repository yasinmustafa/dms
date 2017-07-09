package com.dms.model.entity;
// Generated 26-May-2014 11:50:44 by Hibernate Tools 3.4.0.CR1


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * Person generated by hbm2java
 */
@Entity
@Audited
@Table(name="person")
public class Person  extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="PER_ID", unique=true, nullable=false)
     private Integer perId;
	
	@Column(name="FIRST_NAME", length=4000)
     private String firstName;
	
	@Column(name="SURNAME", length=4000)
     private String surname;
	
	@Column(name="COMPANY_IND")
     private Integer companyInd;
	
	@Column(name="COMPANY_NAME", length=4000)
     private String companyName;
	
	@Column(name="SORT_ORDER")
     private Integer sortOrder;

    public Person() {
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

