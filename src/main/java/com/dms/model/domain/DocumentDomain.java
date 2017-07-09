package com.dms.model.domain;

import java.util.Date;
public class DocumentDomain extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
     
     private PersonDomain personByDocSentFromPerId;
     
     private TypeDomain type;
     
     private PersonDomain personByDocSentToPerId;
     
     private String filename;
     
     private Date indexedDate;
     
     private String comText;
     
     private String ext;
     
     private Date dateOnLetter;
     
     private String createdBy;
     
     private String lastUpdatedBy;
     
     private Date lastUpdatedDate;
     
     private Boolean autoInserted;
     
     private LetterDomain letter;

    public DocumentDomain() {
    }
 

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public PersonDomain getPersonByDocSentFromPerId() {
        return this.personByDocSentFromPerId;
    }
    
    public void setPersonByDocSentFromPerId(PersonDomain personByDocSentFromPerId) {
        this.personByDocSentFromPerId = personByDocSentFromPerId;
    }

    public TypeDomain getType() {
        return this.type;
    }
    
    public void setType(TypeDomain type) {
        this.type = type;
    }

    public PersonDomain getPersonByDocSentToPerId() {
        return this.personByDocSentToPerId;
    }
    
    public void setPersonByDocSentToPerId(PersonDomain personByDocSentToPerId) {
        this.personByDocSentToPerId = personByDocSentToPerId;
    }

    
    public String getFilename() {
        return this.filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getIndexedDate() {
        return this.indexedDate;
    }
    
    public void setIndexedDate(Date indexedDate) {
        this.indexedDate = indexedDate;
    }

    public String getComText() {
        return this.comText;
    }
    
    public void setComText(String comText) {
        this.comText = comText;
    }
    
    public String getExt() {
        return this.ext;
    }
    
    public void setExt(String ext) {
        this.ext = ext;
    }

    public Date getDateOnLetter() {
        return this.dateOnLetter;
    }
    
    public void setDateOnLetter(Date dateOnLetter) {
        this.dateOnLetter = dateOnLetter;
    }

    
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    
    public Boolean getAutoInserted() {
        return this.autoInserted;
    }
    
    public void setAutoInserted(Boolean autoInserted) {
        this.autoInserted = autoInserted;
    }

    public LetterDomain getLetter() {
		return letter;
	}

	public void setLetter(LetterDomain letter) {
		this.letter = letter;
	}



}


