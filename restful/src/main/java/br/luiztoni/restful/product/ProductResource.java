package br.luiztoni.restful.product;

import java.io.File;
import java.util.List;

import br.luiztoni.restful.category.Category;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
@Path("/products")
public class ProductResource {
	@Inject
	private ProductRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Transactional
	public Response index() {
		List<Product> products = repository.index();
		return Response.ok(products).build();
	}

	@GET
	@Path("{id}")
	@Transactional
	@RolesAllowed("basic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response show(@PathParam("id") int id) {
		Product product = repository.read(id);
		if (product == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(product).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("basic")
    @Transactional
	public Response create(ProductRequest request) {
		Product product = new Product(request.name(), request.description(), new Category(request.category()));
		repository.create(product);
		return Response.status(201).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Transactional
	public Response update(Product product) {
		repository.update(product);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Transactional
	@RolesAllowed("basic")
	public Response delete(@PathParam("id") long id) {
		repository.delete(id);
		return Response.ok().build();
	}
	
	@GET
    @Path("/images/{name}")
    @Produces("image/jpg")
    @PermitAll
    public Response getImageFile(@PathParam("name") String name) {
 
        File file = new File("/home/luiz/Imagens/"+name);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=\"test_image_file.png\"");
        return response.build();
 
    }

}
