package com.dms.model.domain;

public class LetterTextDomain extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer letId;

	private Integer sequence;

	private String text;

	private String extract;

	public LetterTextDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getExtract() {
		return extract;
	}

	public void setExtract(String extract) {
		this.extract = extract;
	}

	public Integer getLetId() {
		return letId;
	}

	public void setLetId(Integer letId) {
		this.letId = letId;
	}

}
