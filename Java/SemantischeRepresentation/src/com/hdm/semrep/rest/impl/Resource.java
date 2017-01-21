package com.hdm.semrep.rest.impl;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.hdm.semrep.showcase.*;

@Path("/rest/")
public class Resource {
 
 @GET
 @Produces("text/plain")
 @Path("/methode1") 
 public String getEmployee() {
	 
	 String test = "";
	 
	 //String sz1 = new Szenario1().getDocumentsByProjectName();
	 	
        return "Ausgabe-Links:";
    }
 

 @GET
 @Produces("text/plain")
 @Path("/methode2") 
 public String getTurk() {
        return "Methode 2 sagt Jaaaaa";
    }
 
}