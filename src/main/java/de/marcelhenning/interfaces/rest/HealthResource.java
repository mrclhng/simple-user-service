package de.marcelhenning.interfaces.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/health")
@Produces(MediaType.TEXT_PLAIN)
public class HealthResource {
    @GET
    public String ok() { return "ok"; }
}
