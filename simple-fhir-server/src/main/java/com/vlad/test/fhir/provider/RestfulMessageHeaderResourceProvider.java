package com.vlad.test.fhir.provider;

import org.apache.log4j.Logger;

import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class RestfulMessageHeaderResourceProvider implements IResourceProvider {

	private static final Logger logger = Logger.getLogger(RestfulMessageHeaderResourceProvider.class);
	
	public Class<MessageHeader> getResourceType() {
		return MessageHeader.class;
	}

	@Create
	public MethodOutcome uploadReport(@ResourceParam MessageHeader report){
		
		logger.info("Uploaded for creation report->"+report.getText().getDivAsString());
		
		MethodOutcome retVal = new MethodOutcome();
		
		retVal.setId(new IdDt("MessageHeader", System.currentTimeMillis() + "", "1"));			
				
		return retVal;
	}
}
