package br.luiztoni.faces;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

public class SecurityContext {
    private static SecurityContext context;

    private SecurityContext() {}

    public static SecurityContext getContext() {
        if (context == null) {
            context = new SecurityContext();
        }
        return context;
    }

    private ExternalContext getExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("Request error");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public void logout() {
        getExternalContext().invalidateSession();
    }

    public Object getValue(String key) {
        return getExternalContext().getSessionMap().get(key);
    }

    public void setValue(String key, Object value) {
        getExternalContext().getSessionMap().put(key, value);
    }
}

