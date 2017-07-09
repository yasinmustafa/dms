package com.dms.model.domain;

import java.util.Date;


public class ConnectionDomain extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idConnection;
     private Date dateConnected;
     private String ip;
     private String password;
     private String user;
     private String host;
     private String port;
     private String reqUser;
     private String uri;
     private String url;
     private String userAgent;

    public ConnectionDomain() {
    }
   
    public Integer getIdConnection() {
        return this.idConnection;
    }
    
    public void setIdConnection(Integer idConnection) {
        this.idConnection = idConnection;
    }

    public Date getDateConnected() {
        return this.dateConnected;
    }
    
    public void setDateConnected(Date dateConnected) {
        this.dateConnected = dateConnected;
    }

    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    
    public String getHost() {
        return this.host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }

    
    public String getPort() {
        return this.port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }

    
    public String getReqUser() {
        return this.reqUser;
    }
    
    public void setReqUser(String reqUser) {
        this.reqUser = reqUser;
    }

    
    public String getUri() {
        return this.uri;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }

    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}




}


