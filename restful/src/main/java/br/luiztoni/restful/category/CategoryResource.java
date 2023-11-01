package br.luiztoni.restful.category;

import java.util.List;

import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
@Path("/categories")
public class CategoryResource {
    @Inject
    private CategoryRepository repository;
   
    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
        List<Category> categories = repository.index();
        return Response.ok(categories).build();
    }

    @GET
    @Path("{id}")
    @Transactional
	@RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("id") int id) {
        Category category = repository.read(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(category).build();
    }

    @POST
	@RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Category category) {
        repository.create(category);
        return Response.status(201).build();
    }

    @PUT
	@RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Category category) {
        repository.update(category);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
	@RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        repository.delete(id);
        return Response.ok().build();
    }

	@GET
	@Path("/find")
	@Transactional
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Category find(@QueryParam("q") String q) {
		Category category = repository.findByNameLike(q);
		if (category == null) {
			return null;
		}
		return category;
	}
}
