package com.vlad.test.fhir.client.service.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.vlad.test.fhir.client.service.SimpleDiagnosticReportServiceClient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;

public class SimpleDiagnosticReportServiceClientImpl implements SimpleDiagnosticReportServiceClient {

	private static final Logger logger = Logger.getLogger(SimpleDiagnosticReportServiceClientImpl.class);

	private String serverBase = null;

	private FhirContext ctx = null;

	public SimpleDiagnosticReportServiceClientImpl(String serverBase) {
		this.serverBase = serverBase;
		ctx = FhirContext.forDstu2();
	}

	public DiagnosticReport create(DiagnosticReport diagnosticReport) {

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);

		logger.info("diagnosticReport to save->\n"+ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(diagnosticReport));
		
		// Perform a search
		MethodOutcome results = client.create().resource(diagnosticReport).prettyPrint().encodedXml().execute();

		logger.info("DiagnosticReport was created with ID " + results.getId().getValue() + ", results->"
				+ results.toString());

		diagnosticReport.setId(results.getId());

		return diagnosticReport;
	}

	public Serializable create(MessageHeader report) {

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);

		logger.info("report to save->\n"+ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(report));
		
		// Perform a search
		MethodOutcome results = client.create().resource(report).prettyPrint().encodedXml().execute();

		logger.info("Report was created with ID " + results.getId().getValue() + ", results->" + results.toString());

		return results.getId().getValue();
	}

	public Serializable create(Bundle bundleofResources) {

		logger.info("bundle to create ->\n"+ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(bundleofResources));

		// Create a client and post the transaction to the server
		IGenericClient client = ctx.newRestfulGenericClient(serverBase);
		Bundle resp = client.transaction().withBundle(bundleofResources).execute();

		// Log the response
		logger.info(ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(resp));

		return resp.getId().getValue();
	}

}
