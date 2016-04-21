package com.vlad.test.fhir.client.service;

import java.io.Serializable;

import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;

public interface SimpleDiagnosticReportServiceClient {

	DiagnosticReport create(DiagnosticReport diagnosticReport);
	
	Serializable create(MessageHeader report);
	
	Serializable create(Bundle bundleofResources);
}
