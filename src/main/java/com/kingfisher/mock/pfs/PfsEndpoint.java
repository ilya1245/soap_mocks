package com.kingfisher.mock.pfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.kingfisher.mock.GetCountryRequest;
import io.spring.kingfisher.mock.GetCountryResponse;

@Endpoint
public class PfsEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/kingfisher/mock";

	private PfsRepository countryRepository;

	@Autowired
	public PfsEndpoint(PfsRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
}
