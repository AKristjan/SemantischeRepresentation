package com.hdm.semrep.showcase;

import java.io.File;
import java.io.FileReader;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;

/**
 * Das Szenario 1: Proof-of-Concept
 */
public class Szenario1_SelectByDocumentFormat_Formatted {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * 
	 */
	public static void main(String[] args) {
		getDocumentsByDocumentFormat();
	}

	/**
	 * Die Methode <code> getDocumentsByFileFormat()</code> gibt alle
	 * Dokumentnamen und deren Google Drive URL aus. Würden sich zwei
	 * Gesprächspartner über eine vor kurzem erstellte PowerPoint-Präsentation
	 * unterhalten, würden diese angezeigt werden.
	 * 
	 */
	public static void getDocumentsByDocumentFormat() {

		String filePath = "src/data/a-box.owl";
		OntModel ontologyModel = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			ontologyModel.read(fileReader, null);

			// Ausgabe aller Dokumente, samt deren Google Drive URL mit dem
			// Dateiformat: PowerPoint-Präsentation (.pptx) vom November 2016
			String sparql = "PREFIX foaf: <http://www.semanticweb.org/bjorn/ontologies/2016/11/ab_cloud#>"
					+ "	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ " PREFIX mebase: <http://www.semanticweb.org/bjorn/ontologies/2016/11/tb_cloud#>"
					+ " SELECT ?Dokumentname ?URL "
					+ " WHERE  { "
					+ " ?x foaf:URL ?URL ."
					+ " ?x foaf:Dokumentname ?Dokumentname ."
					+ " ?x foaf:DokumentTyp 'PowerPoint-Präsentation (.pptx)' ."
					+ " ?x foaf:ErstellugsDatum ?ErstellugsDatum ."
					+ " Filter ( str(?ErstellugsDatum) > '2016-11-01T00:30:00' )"
					+ "	}";

			// Initialisierung und Ausführung einer SPARQL-Query
			Query query = QueryFactory.create(sparql);
			QueryExecution queryExecution = QueryExecutionFactory.create(query,
					ontologyModel);

			// Initialisierung von Resultset für Ergebniswerte der SPARQL-Query
			ResultSet resultSet = queryExecution.execSelect();

			// Ergebniswerte werden für Konsolendarstellung aufbereitet
			ResultSetFormatter.out(System.out, resultSet, query);
			
			queryExecution.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
