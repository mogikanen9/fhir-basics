package com.vlad.test.fhir.client.service.impl;

import org.apache.log4j.Logger;

import com.vlad.test.fhir.client.service.SimpleDiagnosticReportServiceClient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;

public class SimpleDiagnosticReportServiceClientImpl implements SimpleDiagnosticReportServiceClient {

	private static final Logger logger = Logger.getLogger(SimpleDiagnosticReportServiceClientImpl.class);

	private String serverBase = null;
	
	
	public SimpleDiagnosticReportServiceClientImpl(String serverBase){
		this.serverBase = serverBase;
	}
	
	public DiagnosticReport create(DiagnosticReport diagnosticReport) {
		
		FhirContext ctx = FhirContext.forDstu2();		

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);

		// Perform a search
		MethodOutcome results = client.create().resource(diagnosticReport).prettyPrint().encodedXml().execute();

		logger.info("Created with ID " + results.getId().getValue() + " patients named 'duck'");

		diagnosticReport.setId(results.getId());

		return diagnosticReport;
	}

}
