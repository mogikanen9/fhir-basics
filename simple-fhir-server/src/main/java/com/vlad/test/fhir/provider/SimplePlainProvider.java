package com.vlad.test.fhir.provider;

import java.util.List;

import org.apache.log4j.Logger;

import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;

public class SimplePlainProvider {

	private static final Logger logger = Logger.getLogger(SimplePlainProvider.class);
	
	@Transaction
	public Bundle transaction(@TransactionParam Bundle theInput) {
		
		List<Entry> enties = theInput.getEntry();
		for (Entry nextEntry : enties) {			 
			 logger.info("entry resource->"+nextEntry.getResource().getClass());
		}
			 
	 
	   Bundle retVal = new Bundle();
	   // Populate return bundle
	   return retVal;
	}

}
