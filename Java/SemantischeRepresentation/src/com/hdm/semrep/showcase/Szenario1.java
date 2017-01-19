package com.hdm.semrep.showcase;

import java.io.File;

import java.io.FileReader;
import java.util.List;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

public class Szenario1 {

	public static void main(String[] args) {
		getDocumentsByProjectName();
	}

	public static void getDocumentsByProjectName() {

		String filePath = "src/data/a-box.owl";
		OntModel ontologyModel = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			ontologyModel.read(fileReader, null);

			// Ausgabe aller Dokumente, samt deren Google Drive URL
			String sparql = "PREFIX foaf: <http://www.semanticweb.org/bjorn/ontologies/2016/11/ab_cloud#>"
					+ "	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ " PREFIX mebase: <http://www.semanticweb.org/bjorn/ontologies/2016/11/tb_cloud#>"
					+ " SELECT ?Dokumentname ?URL "
					+ " WHERE  { ?x foaf:URL ?URL ."
					+ " ?x foaf:Dokumentname ?Dokumentname ." + "}";

			Query query = QueryFactory.create(sparql);
			QueryExecution queryExecution = QueryExecutionFactory.create(query, ontologyModel);
			ResultSet resultSet = queryExecution.execSelect();
 
			for (int i = 0; resultSet.hasNext() == true; i++) {
				QuerySolution querySolution = resultSet.nextSolution();
				for (int j = 0; j < resultSet.getResultVars().size(); j++) {
					String results = resultSet.getResultVars().get(j).toString();
					RDFNode rdfNode = querySolution.get(results);
					System.out.println(rdfNode.toString());
				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
