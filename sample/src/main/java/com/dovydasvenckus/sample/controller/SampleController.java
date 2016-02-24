package com.dovydasvenckus.sample.controller;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
 
@Path("/sample")
@Stateless
public class SampleController {
 
    @GET
    @Produces("text/plain")
    public String sample() {
        return "Sample service";
    }
}