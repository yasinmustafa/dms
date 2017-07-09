package com.dms.business.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.dms.business.service.ConnectionService;
import com.dms.business.service.UserService;
import com.dms.model.domain.ConnectionDomain;
import com.dms.model.domain.UserDomain;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 7765876811740798583L;

	private String username;
	private String password;

	private boolean loggedIn;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	@ManagedProperty("#{userService}")
	private UserService userService;

	@ManagedProperty("#{connectionService}")
	private ConnectionService connectionService;

	/**
	 * Login operation.
	 * 
	 * @return
	 */
	public String doLogin() {

		int count = 0;
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("numberOfTries")) {
			count = ((Integer) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("numberOfTries"))
					.intValue();
			if (count == 3) {
				// authenticating failed due to max retries
				FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);

				// To to login page
				return navigationBean.toLogin();
			}
		}
		List<UserDomain> users = userService.findByNameAndPassword(username, password);
		if (users.size() == 1) {
			// Login Success
			loggedIn = true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", username);
			return navigationBean.redirectToWelcome();
		}
		
		// Login Failed
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("numberOfTries", new Integer(count + 1));

		FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// To to login page
		return navigationBean.toLogin();

	}

	/**
	 * Logout operation.
	 * 
	 * @return
	 */
	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		loggedIn = false;

		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return navigationBean.toLogin();
	}

	// ------------------------------
	// Getters & Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ConnectionService getConnectionService() {
		return connectionService;
	}

	public void setConnectionService(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

}