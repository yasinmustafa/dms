package com.dms.model.domain;
public class TypeDomain extends BaseDomain implements java.io.Serializable, Comparable <TypeDomain> {

	private static final long serialVersionUID = 1L;

		private Integer typId;
		
	    private String name;
		
	    private String category;
		
	    private Integer sortOrder;

	    public Integer getTypId() {
	        return this.typId;
	    }
	    
	    public void setTypId(Integer typId) {
	        this.typId = typId;
	    }

	    public String getName() {
	        return this.name;
	    }
	    
	    public void setName(String name) {
	        this.name = name;
	    }

	    
	    public String getCategory() {
	        return this.category;
	    }
	    
	    public void setCategory(String category) {
	        this.category = category;
	    }

	    
	    public Integer getSortOrder() {
	        return this.sortOrder;
	    }
	    
	    public void setSortOrder(Integer sortOrder) {
	        this.sortOrder = sortOrder;
	    }

		@Override
		public int compareTo(TypeDomain o) {
			if (this.sortOrder==null || o.sortOrder==null)
				return 0;
			return this.sortOrder.compareTo(o.sortOrder);
		}

	}


