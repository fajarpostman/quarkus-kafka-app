package com.fajar.entrypoint.rest;

import com.fajar.domain.model.UserData;
import com.fajar.repository.UserDataRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserDataRepository repository;

    @GET
    public List<UserData> getAll() {
        return repository.listAll();
    }
}
