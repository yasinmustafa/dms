package com.dms.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LetterDomain extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer letId;
	
	private Date metaCreationDate;
	
	private Date metaModifiedDate;
	
	private Date metaSaveDate;
	
	private Date metaCreatedDate;
	
	private Date metaLastModifiedDate;
	
	private Date metaDate;

	private Integer metaNoPages;
	
	private String metaProducer;

    private String metaContentType;
	
    private Integer documentId;

	private Set<LetterTextDomain> letterTexts = new HashSet<LetterTextDomain>(0);
    

    
     public Integer getLetId() {
         return this.letId;
     }
     
     public void setLetId(Integer letId) {
         this.letId = letId;
     }

	public Set<LetterTextDomain> getLetterTexts() {
		return letterTexts;
	}

	public void setLetterTexts(Set<LetterTextDomain> letterTexts) {
		this.letterTexts = letterTexts;
	}

	public Date getMetaCreationDate() {
		return metaCreationDate;
	}

	public void setMetaCreationDate(Date metaCreationDate) {
		this.metaCreationDate = metaCreationDate;
	}

	public Date getMetaModifiedDate() {
		return metaModifiedDate;
	}

	public void setMetaModifiedDate(Date metaModifiedDate) {
		this.metaModifiedDate = metaModifiedDate;
	}

	public Date getMetaSaveDate() {
		return metaSaveDate;
	}

	public void setMetaSaveDate(Date metaSaveDate) {
		this.metaSaveDate = metaSaveDate;
	}

	public Date getMetaCreatedDate() {
		return metaCreatedDate;
	}

	public void setMetaCreatedDate(Date metaCreatedDate) {
		this.metaCreatedDate = metaCreatedDate;
	}

	public Date getMetaLastModifiedDate() {
		return metaLastModifiedDate;
	}

	public void setMetaLastModifiedDate(Date metaLastModifiedDate) {
		this.metaLastModifiedDate = metaLastModifiedDate;
	}

	public Date getMetaDate() {
		return metaDate;
	}

	public void setMetaDate(Date metaDate) {
		this.metaDate = metaDate;
	}

	public Integer getMetaNoPages() {
		return metaNoPages;
	}

	public void setMetaNoPages(Integer metaNoPages) {
		this.metaNoPages = metaNoPages;
	}

	public String getMetaContentType() {
		return metaContentType;
	}

	public void setMetaContentType(String metaContentType) {
		this.metaContentType = metaContentType;
	}
	public String getMetaProducer() {
		return metaProducer;
	}
	public void setMetaProducer(String metaProducer) {
		this.metaProducer = metaProducer;
	}

	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

}
