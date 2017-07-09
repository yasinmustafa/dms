package com.dms.model.entity;

import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@MappedSuperclass
@Audited
public class BaseEntity {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="CREATED_BY", length=30)
    private String createdBy;
    
    @Column(name="MODIFIED_BY", length=30)
    private String modifiedBy;

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@PrePersist
	@PreUpdate
	public void updateModifiedTimeStamp() {
		modifiedDate=Calendar.getInstance().getTime();
		if (FacesContext.getCurrentInstance()!=null
				&& FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("userName")) {
			String userName = ((String) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("userName"));
			if (createdBy==null || createdBy.isEmpty())
				createdBy=userName;
			modifiedBy=userName;
		}
	}

}
