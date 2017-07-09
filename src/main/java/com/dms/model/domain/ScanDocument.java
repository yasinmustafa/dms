package com.dms.model.domain;

import java.io.Serializable;
import java.util.Date;

public class ScanDocument implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String docTitle;
	private Date dateOnLetter;
	private String comment;
	private Integer personFrom;
	private Integer personTo;
	private Integer type;
	
	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public Date getDateOnLetter() {
		return dateOnLetter;
	}

	public void setDateOnLetter(Date dateOnLetter) {
		this.dateOnLetter = dateOnLetter;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ScanDocument))
            return false;
        if (obj == this)
            return true;
        ScanDocument other = (ScanDocument) obj;
        return this.docTitle.equals(other.docTitle);
	}

	public Integer getPersonFrom() {
		return personFrom;
	}

	public void setPersonFrom(Integer personFrom) {
		this.personFrom = personFrom;
	}

	public Integer getPersonTo() {
		return personTo;
	}

	public void setPersonTo(Integer personTo) {
		this.personTo = personTo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
