package com.hdm.semrep.rest.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.jena.fuseki.EmbeddedFusekiServer;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import com.hp.hpl.jena.sparql.core.DatasetGraph;
import com.hp.hpl.jena.sparql.core.DatasetGraphFactory;

public class JenaService {
	String fileName = "abox.owl";

 
	 public static EmbeddedFusekiServer mem(int port, String datasetPath) {
	        DatasetGraph dsg = DatasetGraphFactory.createMem() ;
	        return EmbeddedFusekiServer.create(port, dsg, datasetPath) ;
	    }
	
	public static Model fileToModel(String path){

		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			
			try {
				File file = new File(path);
				FileReader reader;
				reader = new FileReader(file);
				model.read(reader,null);
				System.out.println(String.format("asdasdas"));

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	
			model.write(System.out);
		 
		return model;
	}
	
	
	
		 
	
}
