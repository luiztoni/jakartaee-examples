package br.luiztoni.restful.bank;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/banks")
public class BankResource {
	

	@GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
	@PermitAll
    public Response index() {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:3000/api/banks/v1").request(MediaType.APPLICATION_JSON_TYPE).get();
		
		String payload = response.readEntity(String.class);
		Jsonb jsonb = JsonbBuilder.create();
		Bank[] actual = jsonb.fromJson(payload, Bank[].class);
        return Response.ok(actual).build();
    }
	
	@GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@PermitAll
    public Response show(@PathParam("id") int id) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:3000/api/banks/v1/"+id).request(MediaType.APPLICATION_JSON_TYPE).get();
		
		String payload = response.readEntity(String.class);
		Jsonb jsonb = JsonbBuilder.create();
		Bank actual = jsonb.fromJson(payload, Bank.class);
        return Response.ok(actual).build();
    }
}
