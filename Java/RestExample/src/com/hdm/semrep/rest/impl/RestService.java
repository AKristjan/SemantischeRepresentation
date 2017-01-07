package com.hdm.semrep.rest.impl;

import javax.ws.rs.Path;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
@Path("/restservice/")
public class RestService {
 
	
	
	 
	
 @GET
 @Produces("text/plain")
 @Path("/add") 
 public String addData() {
	
	 
		  
	 String UPDATE_TEMPLATE = 
	            "PREFIX dc: <http://purl.org/dc/elements/1.1/>"
	            + "INSERT DATA"
	            + "{ <http://test/%s>    dc:Dokument    \"Neues Pflichtenheft\" ;"
	            + "                         dc:Autor  \"Max Mustermann\" ." + "}   ";
	 
	 
	   		//Update Test
 	        try {
 	         
 	        	
 	        	 QueryExecution executionQuery = QueryExecutionFactory.sparqlService(
 	     	 	   "http://http://23.236.50.250:3030/ds/query", "SELECT * WHERE {?x ?r ?y}");
 	     	        	  
 	 	         ResultSet results = executionQuery.execSelect();
 	 	         ResultSetFormatter.out(System.out, results);
 	 	         executionQuery.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
 	        	// Printer
		    	String id = UUID.randomUUID().toString();
				System.out.println(String.format("Hinzugefügt", id));
				UpdateProcessor updateQuery = UpdateExecutionFactory.createRemote(
				       UpdateFactory.create(String.format(UPDATE_TEMPLATE, id)), 
				        "http://23.236.50.250:3030/ds/update");
					updateQuery.execute();
	 
        return "Methode insert:  sagt Jaaaaa!"; 
        
        
 }
 
 
 
 
 @GET
 @Produces("text/plain")
 @Path("/update") 
 public String updateData() {
	 
	 // HIER: Methodenkörper

	 
        return "Methode update: sagt Jaaaaa";
    }
 
 
 @GET
 @Produces("text/plain")
 @Path("/getData") 
 public String getData() {
	 
	 // HIER: Methodenkörper

        return "Methode getData: sagt Jaaaaa";
    }
 
 
 
 
 
 
 //////// n2h ////////
 
 /*
 @GET
 @Produces("text/plain")
 @Path("/getData") 
 public String getDokumentByDriveID() {
	 
	 // HIER: Methodenkörper

        return "Methode update: sagt Jaaaaa";
    }
 
 */

 
 
}