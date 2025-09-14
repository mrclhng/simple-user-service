package de.marcelhenning.interfaces.error;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<IllegalStateException> {
    public Response toResponse(IllegalStateException ex) {
        if ("email_exists".equals(ex.getMessage())) return Response.status(Response.Status.CONFLICT).entity(new ErrorPayload("email_exists")).type(MediaType.APPLICATION_JSON).build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorPayload("error")).type(MediaType.APPLICATION_JSON).build();
    }
    public static class ErrorPayload { public String code; public ErrorPayload(String code) { this.code = code; } }
}
