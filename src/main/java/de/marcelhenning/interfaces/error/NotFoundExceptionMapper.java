package de.marcelhenning.interfaces.error;

import java.util.NoSuchElementException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NoSuchElementException> {
    public Response toResponse(NoSuchElementException ex) {
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorPayload("not_found")).type(MediaType.APPLICATION_JSON).build();
    }
    public static class ErrorPayload { public String code; public ErrorPayload(String code) { this.code = code; } }
}
