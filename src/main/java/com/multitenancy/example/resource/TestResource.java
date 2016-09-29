package com.multitenancy.example.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.multitenancy.example.core.Test;
import com.multitenancy.example.dao.TestDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/tests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class TestResource {
    private final TestDAO dao;

    @Inject
    public TestResource(TestDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("/")
    @Timed
    @UnitOfWork
    public List<Test> getAll(){
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Test get(@PathParam("id") LongParam runId){
        Test test = dao.findById(runId.get());
        if(test == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return test;
    }

    @POST
    @Path("/")
    @Timed
    @UnitOfWork
    public Response create(@Valid Test test){
        Long id = dao.create(test);
        return Response.created(uriFor(test)).build();
    }

    private URI uriFor(Test test) {
        return URI.create(String.format("/tests/%d", test.getId()));
    }

}
