package com.vlad.test.fhir.client.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vlad.test.fhir.client.service.impl.SimpleDiagnosticReportServiceClientImpl;

import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.model.dstu2.valueset.BundleTypeEnum;
import ca.uhn.fhir.model.dstu2.valueset.HTTPVerbEnum;
import ca.uhn.fhir.model.dstu2.valueset.NarrativeStatusEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.XhtmlDt;

import org.junit.Assert;

public class SimplDiagnosticReportServiceClientTestCase {

	private static final Logger logger = Logger.getLogger(SimplDiagnosticReportServiceClientTestCase.class);

	private static final String serverBase = "http://localhost:8082/simple-fhir-server/fhir";
	
	@Test
	public void testCreateDiagnosticReport() {

		
		logger.info("serverBase->" + serverBase);

		SimpleDiagnosticReportServiceClient service = new SimpleDiagnosticReportServiceClientImpl(serverBase);

		DiagnosticReport dReport = new DiagnosticReport();
		dReport.setIssued(new InstantDt(new Date()));
		dReport.setText(new NarrativeDt(new XhtmlDt("test addinola data/test about diagnostic report"), NarrativeStatusEnum.ADDITIONAL));

		logger.info("created DiagnosticReport instance to save on server->" + dReport.toString());

		dReport = service.create(dReport);
		Assert.assertNotNull(dReport);
		Assert.assertNotNull(dReport.getId());

		logger.info("Our dReport was successfully created with id->" + dReport.getId().toString());

	}
	
	@Test
	public void testCreateReport() {

		
		logger.info("serverBase->" + serverBase);

		SimpleDiagnosticReportServiceClient service = new SimpleDiagnosticReportServiceClientImpl(serverBase);

		MessageHeader message = new MessageHeader();
		message.setText(new NarrativeDt(new XhtmlDt("This is a simple report message to be sent via FHIR protocol"), 
				NarrativeStatusEnum.ADDITIONAL));
		message.setTimestamp(new InstantDt(new Date()));					
		

		logger.info("created report/message instance to save on server->" + message.toString());

		Serializable resultId = service.create(message);
		Assert.assertNotNull(resultId);
		
		logger.info("Our dReport was successfully created with id->" + resultId);

	}
	
	@Test
	public void atestCreateBundle() {

		
		logger.info("serverBase->" + serverBase);

		SimpleDiagnosticReportServiceClient service = new SimpleDiagnosticReportServiceClientImpl(serverBase);

		//message header
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setText(new NarrativeDt(new XhtmlDt("Message header from bundle"), 
				NarrativeStatusEnum.ADDITIONAL));
		messageHeader.setTimestamp(new InstantDt(new Date()));
		messageHeader.setId(IdDt.newRandomUuid());
		
		
		//diagnostic report
		DiagnosticReport dReport = new DiagnosticReport();
		dReport.setIssued(new InstantDt(new Date()));
		dReport.setText(new NarrativeDt(new XhtmlDt("test additional data/test about diagnostic report"), NarrativeStatusEnum.ADDITIONAL));
		dReport.setId(IdDt.newRandomUuid());
		
		List<ResourceReferenceDt> data = new ArrayList<ResourceReferenceDt>();
		data.add(new ResourceReferenceDt(dReport.getId().getValue()));
		messageHeader.setData(data);
		
		logger.info("created report/message instance to save on server->" + messageHeader.toString());

		Bundle bundle = new Bundle();
		bundle.setType(BundleTypeEnum.TRANSACTION);
		
		bundle.addEntry()
		   .setFullUrl(messageHeader.getId().getValue())
		   .setResource(messageHeader)
		   .getRequest()
		      .setUrl("MessageHeader")
		      .setMethod(HTTPVerbEnum.POST);
		
		bundle.addEntry()
		   .setFullUrl(dReport.getId().getValue())
		   .setResource(dReport)
		   .getRequest()
		      .setUrl("DiagnosticReport")
		      .setMethod(HTTPVerbEnum.POST);
		
		Serializable resultId = service.create(bundle);
		Assert.assertNotNull(resultId);
		
		logger.info("Our dReport was successfully created with id->" + resultId);

	}

}
