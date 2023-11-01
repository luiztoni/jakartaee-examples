package br.luiztoni.restful.bank;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
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

	private Client client = ClientBuilder.newClient();

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@PermitAll
    public Response index() {
		Response response = client.target("http://localhost:3000/api/banks/v1").request(MediaType.APPLICATION_JSON_TYPE).get();
		
		String payload = response.readEntity(String.class);
		Jsonb jsonb = JsonbBuilder.create();
		Bank[] banks = jsonb.fromJson(payload, Bank[].class);
        return Response.ok(banks).build();
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@PermitAll
    public Response show(@PathParam("id") int id) {
		Response response = client.target("http://localhost:3000/api/banks/v1/"+id).request(MediaType.APPLICATION_JSON_TYPE).get();
		
		String payload = response.readEntity(String.class);
		Jsonb jsonb = JsonbBuilder.create();
		Bank bank = jsonb.fromJson(payload, Bank.class);

		response.close();
		client.close();
        return Response.ok(bank).build();
    }


}
