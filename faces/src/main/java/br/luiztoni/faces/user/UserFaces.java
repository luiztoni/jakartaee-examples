package br.luiztoni.faces.user;

import java.io.Serializable;

import br.luiztoni.faces.SecurityContext;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class UserFaces implements Serializable {

	private static final long serialVersionUID = 1L;

    private String email;

    private String password;

    public String login() {
        if (email.equals("admin@admin.com") && password.equals("adminpwd")) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            SecurityContext.getContext().setValue("user", user);
            return "home/dashboard.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found!", "Invalid Credentials"));
        return null;
    }

    public String logout() {
        SecurityContext.getContext().logout();
		return "../login.xhtml";
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
