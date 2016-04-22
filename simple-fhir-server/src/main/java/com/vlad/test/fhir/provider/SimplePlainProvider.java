package com.vlad.test.fhir.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;

public class SimplePlainProvider {

	private static final Logger logger = Logger.getLogger(SimplePlainProvider.class);
	
	@Transaction
	public Bundle transaction(@TransactionParam Bundle theInput) {
		
		MessageHeader msgHeader = null;
		DiagnosticReport diagnosticReport = null;
		List<Observation> observations = new ArrayList<Observation>(); 
		
		List<Entry> enties = theInput.getEntry();
		for (Entry nextEntry : enties) {			 
			 logger.info("entry resource->"+nextEntry.getResource().getClass());
			 if(MessageHeader.class.isInstance(nextEntry.getResource())){
				 msgHeader = (MessageHeader)nextEntry.getResource();
			 }else if (DiagnosticReport.class.isInstance(nextEntry.getResource())){
				 diagnosticReport = (DiagnosticReport)nextEntry.getResource();
			 }else if (Observation.class.isInstance(nextEntry)){
				 observations.add((Observation)nextEntry.getResource());
			 }else if (Patient.class.isInstance(nextEntry)){
				 
			 }
		}
			 
	 
		if(msgHeader!=null){
			logger.info("msgHeader.getId()->"+msgHeader.getId());
		}
		
		if(diagnosticReport!=null){
			logger.info("diagnosticReport.getId()->"+diagnosticReport.getId());
		}
		
	   Bundle retVal = new Bundle();
	   // Populate return bundle
	   return retVal;
	}

}
