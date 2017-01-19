package com.hdm.semrep.rest.impl;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.core.DatasetGraph;


public class Main {

	public static void main(String[] args) throws IOException {

		// uploadRDF("/Users/mateos_alliaj/Documents/github/SemantischeRepresentation/Java/RestExample/src/data/ab_cloud.owl",
		// "/ab_cloud");

		String tBoxPath = "/Users/mateos_alliaj/Documents/workspace/Prototyp01/src/data/tBoxing.owl";
		String aBoxPath = "/Users/mateos_alliaj/Documents/github/SemantischeRepresentation/Java/RestExample/src/data/ab_cloud.owl";

		OntModel model = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM);

		try {
			File file = new File(aBoxPath);
			FileReader reader = new FileReader(file);
			model.read(reader, null, "RDF/XML");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Dataset dataset = DatasetFactory.create(model);

		// DatasetGraph dsg = DatasetGraphFactory.createTxnMem();
		DatasetGraph dsg = dataset.asDatasetGraph();
		dsg.setDefaultGraph(model.getGraph());
		FusekiEmbeddedServer server = FusekiEmbeddedServer.create()
				.setPort(3330).add("/ds", dsg).build();

		FusekiEmbeddedServer.make(3330, "/ds", dsg).start();

		// Printer
		try {

//			QueryExecution executionQuery = QueryExecutionFactory
//					.sparqlService("http://localhost:3330/ds/query",
//							"SELECT * WHERE {?x ?r ?y}");
			QueryExecution executionQuery = 
					QueryExecutionFactory.sparqlService("http://localhost:3330/ds/query", "SELECT * { ?s ?p ?o}");

			ResultSet results = executionQuery.execSelect();
			ResultSetFormatter.out(System.out, results);
			executionQuery.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// "SELECT ?URL WHERE  { ?Dokument foaf:URL ?URL }");

	}

}