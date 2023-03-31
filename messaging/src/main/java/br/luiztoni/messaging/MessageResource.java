package br.luiztoni.messaging;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
@Stateless
public class MessageResource {

  @Inject
  EventProducer producer;
  
  @GET
  @Path("topic/{msg}")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_PLAIN)
  public Response enqueue1(@PathParam("msg") String message) throws Exception {
    producer.sendToTopic(message);
    return Response.ok().build();
  }

  @GET
  @Path("queue/{msg}")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_PLAIN)
  public Response enqueue(@PathParam("msg") String message) throws Exception {
    producer.sendToQueue(message);
    return Response.ok().build();
  }
}