package br.luiztoni.faces.user;

import br.luiztoni.faces.config.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends AbstractEntity<Long> {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Email
    @NotNull
    @Column(unique = true)
    @Size(min = 10, max = 50)
    private String email;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String password;

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
