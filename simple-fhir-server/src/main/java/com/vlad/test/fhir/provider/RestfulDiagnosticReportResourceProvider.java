package com.vlad.test.fhir.provider;

import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class RestfulDiagnosticReportResourceProvider implements IResourceProvider {

	

	@Create
	public MethodOutcome uploadDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) {

		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdDt("DiagnosticReport", System.currentTimeMillis() + "", "1"));		
		return retVal;
	}

	public Class<DiagnosticReport> getResourceType() {
		// TODO Auto-generated method stub
		return DiagnosticReport.class;
	}
}
