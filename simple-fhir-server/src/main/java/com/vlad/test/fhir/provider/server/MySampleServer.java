package com.vlad.test.fhir.provider.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vlad.test.fhir.provider.RestfulDiagnosticReportResourceProvider;

import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;

@WebServlet(urlPatterns= {"/fhir/*"}, displayName="My FHIR Sample Server")
public class MySampleServer extends RestfulServer{

	private static final long serialVersionUID = 1L;

	@Override
	   protected void initialize() throws ServletException {
	      List<IResourceProvider> resourceProviders = new ArrayList<IResourceProvider>();
	      resourceProviders.add(new RestfulDiagnosticReportResourceProvider());	      
	      setResourceProviders(resourceProviders);
	   }
}
