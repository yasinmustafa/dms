package com.dms.model.domain;

import java.io.Serializable;
import java.util.Date;

public class DocumentDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date indexedDate;
	private Date dateOnLetter;
	private String perFromName;
	private String perToName;
	private Integer perFromId;
	private Integer perToId;
	private String typName;
	private Integer typId;
	private String comText;
	private String ext;
	private String createdBy;
	private Boolean autoInserted;

	public DocumentDetails() {
		
	}
	public DocumentDetails(Integer id, Date indexedDate, Date dateOnLetter,
			String perFromName, String perToName, Integer perFromId,
			Integer perToId, String typName, Integer typId, String comText,
			String ext, String createdBy, Boolean autoInserted) {
		this.id = id;
		this.indexedDate = indexedDate;
		this.dateOnLetter = dateOnLetter;
		this.perFromName = perFromName;
		this.perToName = perToName;
		this.perFromId = perFromId;
		this.perToId = perToId;
		this.typName = typName;
		this.typId = typId;
		this.comText = comText;
		this.ext = ext;
		this.createdBy = createdBy;
		this.autoInserted = autoInserted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Date getIndexedDate() {
		return indexedDate;
	}

	public void setIndexedDate(Date indexedDate) {
		this.indexedDate = indexedDate;
	}

	public Date getDateOnLetter() {
		return dateOnLetter;
	}

	public void setDateOnLetter(Date dateOnLetter) {
		this.dateOnLetter = dateOnLetter;
	}

	public String getPerFromName() {
		return perFromName;
	}

	public void setPerFromName(String perFromName) {
		this.perFromName = perFromName;
	}

	public String getPerToName() {
		return perToName;
	}

	public void setPerToName(String perToName) {
		this.perToName = perToName;
	}

	public Integer getPerFromId() {
		return perFromId;
	}

	public void setPerFromId(Integer perFromId) {
		this.perFromId = perFromId;
	}

	public Integer getPerToId() {
		return perToId;
	}

	public void setPerToId(Integer perToId) {
		this.perToId = perToId;
	}

	public String getTypName() {
		return typName;
	}

	public void setTypName(String typName) {
		this.typName = typName;
	}

	public Integer getTypId() {
		return typId;
	}

	public void setTypId(Integer typId) {
		this.typId = typId;
	}

	public String getComText() {
		return comText;
	}

	public void setComText(String comText) {
		this.comText = comText;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String docCreatedBy) {
		this.createdBy = docCreatedBy;
	}

	public Boolean getAutoInserted() {
		return autoInserted;
	}

	public void setAutoInserted(Boolean dautoInserted) {
		this.autoInserted = dautoInserted;
	}
	
	public String toString() {
		return id + " - " + ext;
	}

}
