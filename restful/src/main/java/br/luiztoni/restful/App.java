package br.luiztoni.restful;

import java.util.HashSet;
import java.util.Set;

import br.luiztoni.restful.bank.BankResource;
import br.luiztoni.restful.category.CategoryResource;
import br.luiztoni.restful.config.CorsFilter;
import br.luiztoni.restful.config.auth.UserFilter;
import br.luiztoni.restful.config.auth.UserResource;
import br.luiztoni.restful.product.ProductResource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@DeclareRoles({"admin", "basic"})
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();
        // filters
        classes.add(CorsFilter.class);
        classes.add(UserFilter.class);
        // resources
        classes.add(CategoryResource.class);
        classes.add(ProductResource.class);
        classes.add(UserResource.class);
        classes.add(BankResource.class);
        return classes;
    }
}
