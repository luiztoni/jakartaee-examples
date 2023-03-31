package br.luiztoni.faces.user;

import br.luiztoni.faces.config.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }

    public User findByEmail(String email) {
        User user;
        try {
            user = (User) manager.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email).getSingleResult();
        } catch (Exception exception) {
            return null;
        }
        return user;
    }
}

