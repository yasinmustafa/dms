package com.dms.model.domain;

import java.io.Serializable;
import java.util.Date;

public class SearchParams implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String docTitle;
	private Date from;
	private Date to;
	private Date indexedFrom;
	private Date indexedTo;
	private String comment;
	private String letterTextSearch;
	private Integer personFrom;
	private Integer personTo;
	private Integer type;
	private Boolean autoInserted;
	private int idFrom;
	private int idTo;
	
	public SearchParams() {
		this.docTitle=null;
		this.from=null;
		this.to=null;
		this.indexedFrom=null;
		this.indexedTo=null;
		this.comment=null;
		this.letterTextSearch=null;
		this.personFrom=null;
		this.personTo=null;
		this.type=null;
		this.autoInserted=null;
		this.idFrom=0;
		this.idTo=0;
	}
	
	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SearchParams))
            return false;
        if (obj == this)
            return true;
        SearchParams other = (SearchParams) obj;
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

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getIndexedFrom() {
		return indexedFrom;
	}

	public void setIndexedFrom(Date indexedFrom) {
		this.indexedFrom = indexedFrom;
	}

	public Date getIndexedTo() {
		return indexedTo;
	}

	public void setIndexedTo(Date indexedTo) {
		this.indexedTo = indexedTo;
	}

	public String getLetterTextSearch() {
		return letterTextSearch;
	}

	public void setLetterTextSearch(String letterTextSearch) {
		this.letterTextSearch = letterTextSearch;
	}

	public Boolean getAutoInserted() {
		return autoInserted;
	}

	public void setAutoInserted(Boolean autoInserted) {
		this.autoInserted = autoInserted;
	}

	public int getIdFrom() {
		return idFrom;
	}

	public void setIdFrom(int idFrom) {
		this.idFrom = idFrom;
	}

	public int getIdTo() {
		return idTo;
	}

	public void setIdTo(int idTo) {
		this.idTo = idTo;
	}

}
