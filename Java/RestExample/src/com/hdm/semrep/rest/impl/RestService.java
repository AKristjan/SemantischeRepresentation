package com.hdm.semrep.rest.impl;


import javax.ws.rs.Path;
import java.io.InputStream;
import java.util.UUID;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

@Path("/restservice/")	
public class RestService {

	@GET
	@Produces("text/plain")
	@Path("/add")
	public String addData() {
		return "";

/*		InputStream in = getClass().getClassLoader().getResourceAsStream(
				"abox.owl");

		String UPDATE_TEMPLATE = "PREFIX dc: <http://purl.org/dc/elements/1.1/>"
				+ "INSERT DATA"
				+ "{ <http://test/%s>    dc:Dokument    \"Neues Pflichtenheft\" ;"
				+ "                         dc:Autor  \"Max Mustermann\" ."
				+ "}   ";

		// Update Test
		try {
			String id = UUID.randomUUID().toString();
			System.out.println(String.format("Hinzugefügt", id));
			UpdateProcessor updateQuery = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(UPDATE_TEMPLATE, id)),
					"http://23.236.50.250:3030/ds/update");
			updateQuery.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Printer
		try {

			QueryExecution executionQuery = QueryExecutionFactory
					.sparqlService("http://localhost:3030/ds/query",
							"SELECT * WHERE {?x ?r ?y}");

			ResultSet results = executionQuery.execSelect();
			ResultSetFormatter.out(System.out, results);
			executionQuery.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Methode insert:  sagt Jaaaaa!";*/

	}

	@GET
	@Produces("text/plain")
	@Path("/update")
	public String updateData() {

		// HIER: Methodenkörper

		final String UPDATE_TEMPLATE = "PREFIX dc: <http://purl.org/dc/elements/1.1/>"
				+ "INSERT DATA"
				+ "{ <http://test/%s>    dc:Dokument    \"Neues Pflichtenheft\" ;"
				+ "                         dc:Autor  \"Max Mustermann\" ."
				+ "}   ";

		// String url = "/RestExample/src/com/hdm/semrep/rest/impl/abox.owl";

		String id = UUID.randomUUID().toString();
		System.out.println(String.format("Adding %s", id));
		UpdateProcessor upp = UpdateExecutionFactory.createRemote(
				UpdateFactory.create(String.format(UPDATE_TEMPLATE, id)),
				"http://23.236.50.250:3030/ds/update");
		upp.execute();

		QueryExecution qe = QueryExecutionFactory.sparqlService(
				"http://23.236.50.250:3030/ds/query",
				"SELECT * WHERE {?x ?r ?y}");
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results);

		qe.close();

		// DatasetGraph dsg = DatasetGraphFactory.createMem() ;
		// EmbeddedFusekiServer.create(3030, dsg, url);

		// FusekiEmbeddedServer server;
		// DatasetGraph serverdsg = DatasetGraphFactory.createTxnMem() ;
		//
		// DatasetGraph dsg = DatasetGraphFactory.createTxnMem() ;
		// server = FusekiEmbeddedServer.create()
		// .setPort(2244)
		// .add("/ds", serverdsg)
		// .build() ;
		//
		// server.start() ;

		return "Methode update: sagt Jaaaaa";
	}

	// Model m2=ModelFactory.createDefaultModel();

	@GET
	@Produces("text/plain")
	@Path("/getData")
	public String getData() {

		// HIER: Methodenkörper

		return "Methode getData: sagt Jaaaaa";
	}

	// ////// n2h ////////

	/*
	 * @GET
	 * 
	 * @Produces("text/plain")
	 * 
	 * @Path("/getData") public String getDokumentByDriveID() {
	 * 
	 * // HIER: Methodenkörper
	 * 
	 * return "Methode update: sagt Jaaaaa"; }
	 */

}