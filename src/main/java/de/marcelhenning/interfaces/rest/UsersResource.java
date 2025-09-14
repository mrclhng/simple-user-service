package de.marcelhenning.interfaces.rest;

import de.marcelhenning.app.dto.UserDTO;
import de.marcelhenning.app.usecase.CreateUser;
import de.marcelhenning.app.usecase.GetUser;
import de.marcelhenning.app.usecase.ListUsers;
import de.marcelhenning.app.usecase.UpdateUser;
import de.marcelhenning.interfaces.rest.dto.UserCreateRequest;
import de.marcelhenning.interfaces.rest.dto.UserResponse;
import de.marcelhenning.interfaces.rest.dto.UserUpdateRequest;
import de.marcelhenning.interfaces.rest.mapper.RestUserMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {
    @Inject CreateUser createUser;
    @Inject UpdateUser updateUser;
    @Inject GetUser getUser;
    @Inject ListUsers listUsers;

    @GET
    public List<UserResponse> list(@QueryParam("page") @DefaultValue("0") int page,
                                   @QueryParam("size") @DefaultValue("50") int size) {
        return listUsers
                .execute(page, size)
                .stream()
                .map(RestUserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public UserResponse get(@PathParam("id") String id) {
        return RestUserMapper.toResponse(getUser.execute(id));
    }

    @POST
    public Response create(@Valid UserCreateRequest req, @Context UriInfo uri) {
        UserDTO dto = createUser.execute(req.firstname, req.lastname, req.email, req.birthday, req.password);
        UserResponse body = RestUserMapper.toResponse(dto);
        URI location = uri.getAbsolutePathBuilder().path(body.id).build();
        return Response.created(location).entity(body).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, @Valid UserUpdateRequest req) {
        updateUser.execute(id, req.firstname, req.lastname, req.email, req.birthday, req.password);
        return Response.noContent().build();
    }
}
