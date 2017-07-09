package com.dms.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="letter")
public class Letter extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="LET_ID", unique=true, nullable=false)
    private Integer letId;
	
	@OneToOne
	@JoinColumn(name="DOC_ID")
    private Document document;
	
	@Temporal(TemporalType.DATE)
	@Column(name="META_CREATION_DATE", length=10)
	private Date metaCreationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="META_MODIFIED_DATE", length=10)
	private Date metaModifiedDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="META_SAVE_DATE", length=10)
	private Date metaSaveDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="META_CREATED_DATE", length=10)
	private Date metaCreatedDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="META_LAST_MODIFIED_DATE", length=10)
	private Date metaLastModifiedDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="META_DATE", length=10)
	private Date metaDate;
	
	@Column(name="META_NO_PAGES")
	private Integer metaNoPages;
	
	@Column(name="META_PRODUCER", length=250)
    private String metaProducer;
	
	@Column(name="META_CONTENT_TYPE", length=250)
    private String metaContentType;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="letter")
	private Set<LetterText> letterTexts = new HashSet<LetterText>(0);
    
	public Set<LetterText> getLetterTexts() {
		return letterTexts;
	}

	public void setLetterTexts(Set<LetterText> letterTexts) {
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

	public String getMetaProducer() {
		return metaProducer;
	}

	public void setMetaProducer(String metaProducer) {
		this.metaProducer = metaProducer;
	}

	public String getMetaContentType() {
		return metaContentType;
	}

	public void setMetaContentType(String metaContentType) {
		this.metaContentType = metaContentType;
	}

	public Integer getLetId() {
		return letId;
	}

	public void setLetId(Integer letId) {
		this.letId = letId;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
