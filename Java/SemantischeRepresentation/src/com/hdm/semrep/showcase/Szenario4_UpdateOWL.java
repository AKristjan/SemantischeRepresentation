package com.hdm.semrep.showcase;

import java.util.UUID;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

/**
 * Das Szenario 3: Proof-of-Concept
 */
public class Szenario4_UpdateOWL {

	private static String dokumentName;
	private static String dokumentTyp;
	private static String dokument_hat_Autor;
	private static String erstellugsDatum;
	private static String aenderungsDatum;
	private static String gehoert_zu_Projekt;
	private static String dokumentURL;
	private static String dokumentVersion;

	/**
	 * Um SPARQL-Queries ausführen zu können, bedarf es einem Server, der diese
	 * entgegen nimmt.
	 * 
	 * In diesem Szenario haben wir einmal lokal einen Apache Jena Fuseki Server
	 * und einmal live auf der Google Compute Engine installiert.
	 * 
	 * Ist der Fuseki-Server installiert und konfiguriert, kann man ihn in
	 * Memory, als Service oder als Standalone-Server betreiben.
	 * 
	 * Zu Demonstrationzwecken läuft der Fuseki-Server lokal mit einem in Memory
	 * Dataset und auf der Google Compute Engine über einen Service mit einem
	 * persistenten Dataset.
	 * 
	 * Der Fuseki-Server kann nach Einrichtung über http://localhost:3030
	 * aufgerufen werden.
	 * 
	 * Für Weitere Informationen siehe Apache Jena Fuseki Dokumentation:
	 * (https://jena.apache.org/documentation/fuseki2/)
	 */
	public static void main(String[] args) {
		updateOWL();
	}

	/**
	 * Die Methode <code> updateOWL()</code> ergänzt die A-Box um weitere
	 * Dokument-Instanzen
	 */
	public static void updateOWL() {

		// Initialisierung der Variablen
		dokumentName = "timing";
		dokumentTyp = "PowerPoint-Präsentation (.pptx)";
		dokument_hat_Autor = "Haruki_Murakami";
		erstellugsDatum = "2017-01-21T15:08:00";
		aenderungsDatum = "2017-01-21T15:08:00";
		gehoert_zu_Projekt = "HighNet";
		dokumentURL = "https://drive.google.com/BeispielURL/4";
		dokumentVersion = "1";

		// Ergänzung der Ontologie um weitere Dokument-Instanzen
		String sparql_insert = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX tb_cloud: <http://www.semanticweb.org/bjorn/ontologies/2016/11/tb_cloud#> "
				+ "PREFIX ab_cloud: <http://www.semanticweb.org/bjorn/ontologies/2016/11/ab_cloud#> "
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
				+ "INSERT DATA { "
				+ "<http://www.semanticweb.org/bjorn/ontologies/2016/11/ab_cloud#"
				+ dokumentName
				+ "> "
				+ "a owl:NamedIndividual ; "
				+ "ab_cloud:Dokumentname '"
				+ dokumentName
				+ "' ; "
				+ "ab_cloud:DokumentTyp '"
				+ dokumentTyp
				+ "' ; "
				+ "ab_cloud:Dokument_hat_Autor  <"
				+ dokument_hat_Autor
				+ "> ; "
				+ "ab_cloud:ErstellugsDatum '"
				+ erstellugsDatum
				+ "^^xsd:dateTime' ; "
				+ "ab_cloud:AenderungsDatum '"
				+ aenderungsDatum
				+ "^^xsd:dateTime' ; "
				+ "ab_cloud:gehoert_zu_Projekt <"
				+ gehoert_zu_Projekt
				+ "> ; "
				+ "ab_cloud:URL '"
				+ dokumentURL
				+ "' ; "
				+ "ab_cloud:Version '" + dokumentVersion + "' ; " + " }";

		try {
			// Ausführung des Update-Kommandos auf dem lokalen Fuseki-Server
			String stringUUID = UUID.randomUUID().toString();
			UpdateProcessor updateProcessor = UpdateExecutionFactory
					.createRemote(UpdateFactory.create(String.format(
							sparql_insert, stringUUID)),
							"http://localhost:3030/ds/update");

			// ### Update Kommando auf Google Compute Engine/Fuseki-Server
			// (Liveumgebung) ###
			// UpdateProcessor updateProcessor = UpdateExecutionFactory
			// .createRemote(UpdateFactory.create(String.format(
			// sparql_insert, stringUUID)),
			// "http://23.236.50.250:3030/ds/update");

			updateProcessor.execute();

			System.out.println("Neues Individual wurde hinzugefügt - ID: "
					+ String.format(stringUUID));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	

}
