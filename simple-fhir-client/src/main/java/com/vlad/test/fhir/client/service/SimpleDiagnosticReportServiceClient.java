package com.vlad.test.fhir.client.service;

import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;

public interface SimpleDiagnosticReportServiceClient {

	DiagnosticReport create(DiagnosticReport diagnosticReport);
}
