package com.dovydasvenckus.timelogger.controller;

import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
 
@Stateless
@ApplicationPath("/sample")
public class SampleController {
 
    @GET
    @Produces("text/plain")
    public String sample() {
        return "Sample service";
    }
}