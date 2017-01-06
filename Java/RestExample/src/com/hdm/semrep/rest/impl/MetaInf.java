package com.hdm.semrep.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/rest2/")
public class MetaInf {

	 
	 @GET
	 @Produces("text/plain")
	 @Path("/methode3") 
	 public String getEmployee() {
	        return "juuu!";
	    }
	}