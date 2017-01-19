package com.hdm.semrep.rest.impl;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.sparql.sse.SSE;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.system.Txn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FusekiEmbeddedServerExample01 {
	
	public static void main(String[] args) {
		example6();
	}
	
	public static void example6() {
		
		LogCtl.setJavaLogging();
		Logger LOG = LoggerFactory.getLogger(FusekiEmbeddedServerExample01.class) ;
		DatasetGraph dsg = DatasetGraphFactory.createTxnMem() ;
		FusekiEmbeddedServer server = FusekiEmbeddedServer.make(3330, "/ds", dsg) ;
		server.start() ;
		LOG.info("Remote 1") ;
		LOG.debug("Hello World");
		try { 
		QueryExecution qExec = 
		QueryExecutionFactory.sparqlService("http://localhost:3330/ds/query", "SELECT * { ?s ?p ?o}");
		// Empty table QueryExecUtils.executeQuery(qExec); }

		// Transaction-protected update.
		Txn.executeWrite(dsg, ()->{ Quad q = SSE.parseQuad("(_ :s :p _:b)") ; dsg.add(q); });

		LOG.info("Remote 2") ;
		
		QueryExecution qExec1 = QueryExecutionFactory.sparqlService("http://localhost:3330/ds/query", "SELECT * { ?s ?p ?o}");
		 // One row. QueryExecUtils.executeQuery(qExec); 
		ResultSet results = qExec1.execSelect();
	    ResultSetFormatter.out(System.out, results);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		server.stop();
		}
	

}
