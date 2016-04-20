package com.vlad.test.fhir.client.service;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vlad.test.fhir.client.service.impl.SimpleDiagnosticReportServiceClientImpl;

import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import org.junit.Assert;

public class SimplDiagnosticReportServiceClientTestCase {

	private static final Logger logger = Logger.getLogger(SimplDiagnosticReportServiceClientTestCase.class);

	@Test
	public void testCreate() {

		String serverBase = "http://localhost:8082/simple-fhir-server/fhir";

		logger.info("serverBase->" + serverBase);

		SimpleDiagnosticReportServiceClient service = new SimpleDiagnosticReportServiceClientImpl(serverBase);

		DiagnosticReport dReport = new DiagnosticReport();

		logger.info("created DiagnosticReport instance to save on server->" + dReport.toString());

		dReport = service.create(dReport);
		Assert.assertNotNull(dReport);
		Assert.assertNotNull(dReport.getId());

		logger.info("Our dReport was successfully created with id->" + dReport.getId().toString());

	}

}
