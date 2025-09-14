package de.marcelhenning.interfaces.error;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.*;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    public Response toResponse(ConstraintViolationException ex) {
        List<Map<String, String>> errors = ex.getConstraintViolations().stream().map(this::map).toList();
        return Response.status(Response.Status.BAD_REQUEST).entity(Collections.singletonMap("errors", errors)).type(MediaType.APPLICATION_JSON).build();
    }
    private Map<String, String> map(ConstraintViolation<?> v) {
        Map<String, String> m = new HashMap<>();
        m.put("path", v.getPropertyPath().toString());
        m.put("message", v.getMessage());
        return m;
    }
}
