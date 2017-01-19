package com.hdm.semrep.rest.impl;

import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


//import com.google.gwt.user.server.rpc;
import com.google.gwt.user.server.rpc.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;


import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RIOT;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.fuseki.FusekiLogging;
import org.apache.jena.fuseki.Fuseki;
//import org.apache.jena.fuseki.embedded.FusekiEmbeddedServer ;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.system.Txn;
import org.json.JSONObject;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

//@Path("/restservice/")
//@RemoteServiceRelativePath("/restservice/")
@SuppressWarnings("serial")
public class RestServiceExample02 {
		//extends RemoteServiceServlet implements IRestService {
	//private static JSONObject jsonObject;

	//private final HashMap<String, Documents> documents = new HashMap<String, Documents>();

	public RestServiceExample02() {
		updateData();
		// addDocumentMetadata();
	}

	@POST
	@Path("/AddDocumentMetadata")
	@Consumes("application/x-www-form-urlencoded")
	public static void addDocumentMetadata(@FormParam("name") String name,
			@FormParam("driveID") String driveID,
			@FormParam("status") String status,
			@FormParam("keywords") String keywords,
			@FormParam("drivePath") String drivePath,
			@FormParam("version") String version,
			@FormParam("creationDate") String creationDate,
			@FormParam("createdBy") String createdBy,
			@FormParam("project") String project,
			@FormParam("type") String type,
			@FormParam("documentClass") String documentClass) {

		System.out.println("addDocumentMetadata");
		try {
			String UPDATE_TEMPLATE = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
					+ " PREFIX tb_cloud: <http://www.semanticweb.org/bjorn/ontologies/2016/11/tb_cloud#>"
					+ " PREFIX tb_cloud_old: <http://www.semanticweb.org/bjorn/ontologies/2016/11/tb_cloud#>"
					+ " INSERT DATA { "
					+ " <http://www.semanticweb.org/bjorn/ontologies/2016/11/ab_cloud#"
					+ name
					+ "> "
					+ " tb_cloud:Dokumentenname '"
					+ name
					+ "' ;"
					+ " tb_cloud_old:Status '"
					+ status
					+ "' ;"
					+ " tb_cloud_old:DriveDocumentID '"
					+ driveID
					+ "' ;"
					+ " tb_cloud_old:Schlagwort "
					+ keywords
					+ " ;"
					+ " tb_cloud_old:Speicherort '"
					+ drivePath
					+ "' ;"
					+ " tb_cloud_old:Version '"
					+ version
					+ "^^xsd:double' ;"
					+ " tb_cloud_old:Erstellungsdatum '"
					+ creationDate
					+ "^^xsd:dateTime' ;"
					+ " tb_cloud_old:Dokument_hat_Verfasser  <"
					+ createdBy
					+ "> ;"
					+ " tb_cloud_old:Dokument_gehoert_zu_Projekt <"
					+ project
					+ "> ;"
					+ " tb_cloud_old:Dokumenttyp '"
					+ type
					+ "' ; " + " rdf:type <" + documentClass + "> ;" + " }";

			String id = UUID.randomUUID().toString();
			UpdateProcessor upp = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(UPDATE_TEMPLATE, id)),
					"http://localhost:8888/ds/update");
			upp.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces("text/plain")
	@Path("/update")
	@Consumes("application/sparql-query")
	public String updateData() {

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
		
		
		System.out.println("UpdateFactory.create() successfully!");

		upp.execute();

		QueryExecution qe = QueryExecutionFactory.sparqlService(
				"http://23.236.50.250:3030/ds/query",
				"SELECT * WHERE {?x ?r ?y}");
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results);

		qe.close();

//		DatasetGraph dsg = DatasetGraphFactory.createMem() ;
//		EmbeddedFusekiServer.create(3030, dsg, url);

//		FusekiEmbeddedServer server;
//		 DatasetGraph serverdsg = DatasetGraphFactory.createTxnMem() ;
//		
//		 DatasetGraph dsg = DatasetGraphFactory.createTxnMem() ;
//		 server = FusekiEmbeddedServer.create()
//		 .setPort(2244)
//		 .add("/ds", serverdsg)
//		 .build() ;
//		
//		 server.start() ;
//		 
//		 System.out.println("gestartet");
//		 
//		 server.stop();
//		 
//		 System.out.println("gestoppt");

		return "Methode update: sagt Jaaaaa";
	}

/*	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		System.out.println("doGet");
		PrintWriter out = resp.getWriter();
		out.println("<html><h1>It works!!</h1></html>");

	};*/

//	@Override
//	protected void doPut(javax.servlet.http.HttpServletRequest req,
//			javax.servlet.http.HttpServletResponse resp)
//			throws javax.servlet.ServletException, java.io.IOException {
//		super.doPost(req, resp);
//		System.out.println("doPost");
//	};

//	@Override
//	public ArrayList<Documents> getProjectDetails() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
