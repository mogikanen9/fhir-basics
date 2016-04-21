package com.vlad.test.fhir.provider;

import org.apache.log4j.Logger;

import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class RestfulDiagnosticReportResourceProvider implements IResourceProvider {

	private static final Logger logger = Logger.getLogger(RestfulDiagnosticReportResourceProvider.class);

	@Create
	public MethodOutcome uploadDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) {

		logger.info("uploaded for creation diagnosticReport->"+diagnosticReport.toString());
		
		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdDt("DiagnosticReport", System.currentTimeMillis() + "", "1"));			
				
		return retVal;
	}

	public Class<DiagnosticReport> getResourceType() {		
		return DiagnosticReport.class;
	}
}
