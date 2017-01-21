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
 * Das Szenario 2: Proof-of-Concept
 */
public class Szenario2_SelectByDocumentnameAndAuthor_Formatted {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		getDocumentsByDocumentnameAndAuthor();
	}

	/**
	 * Die Methode <code> getDocumentsByDocumentnameAndAuthor()</code> gibt alle
	 * Dokumentnamen und deren Google Drive URL aus. Keywords für die Suche sind
	 * der Dokumentname und der Autor des Dokuents.
	 */
	public static void getDocumentsByDocumentnameAndAuthor() {

		String filePath = "src/data/a-box.owl";
		OntModel ontologyModel = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			ontologyModel.read(fileReader, null);

			// Ausgabe von Dokumenten, samt deren Google Drive URL. Die
			// Abfrage-Keywords sind der Dokumentname und der Autor
			String sparql = "PREFIX foaf: <http://www.semanticweb.org/bjorn/ontologies/2016/11/ab_cloud#>"
					+ "	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ " PREFIX mebase: <http://www.semanticweb.org/bjorn/ontologies/2016/11/tb_cloud#>"
					+ " SELECT ?Dokumentname ?URL ?Autor ?Projekt "
					+ " WHERE  { "
					+ " ?Dokument foaf:URL ?URL ."
					+ " ?Dokument foaf:Dokumentname ?Dokumentname ."
					+ " ?Dokument foaf:Dokument_hat_Autor ?Autor ."
					+ " ?Dokument foaf:gehoert_zu_Projekt ?Projekt."
					+ " FILTER regex( str(?Autor), 'Haruki_Murakami' )"
					+ " FILTER regex( str(?Dokumentname), 'Projektplan' )"
					+ "}";

			// Inizialisierung und Ausführung einer SPARQL-Query
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
