package com.hdm.semrep.rest.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.util.FileManager;

public class JenaService {
	String fileName = "abox.owl";

 
	public Model fileToModel(){

		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			
			try {
				File file = new File(fileName);
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
