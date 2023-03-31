package br.luiztoni.restful.config.auth;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("auth")
public class UserResource {
    @Inject
    private UserRepository repository;

    @POST
    @Path("register")
    public Response register(@Valid User user) {
        User auth = repository.findByEmail(user.getEmail());
        if (auth != null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        user.setPassword(Password.encode(user.getPassword(), "mysalt"));
        repository.create(user);
        return Response.status(201).build();
    }
}