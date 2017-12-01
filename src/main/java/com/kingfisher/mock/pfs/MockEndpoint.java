package com.kingfisher.mock.pfs;

import com.kingfisher.oagis._9.*;
import org.openapplications.oagis._9.ApplicationAreaType;
import org.openapplications.oagis._9.ResponseExpressionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import io.spring.kingfisher.mock.GetCountryRequest;
import io.spring.kingfisher.mock.GetCountryResponse;

import javax.xml.bind.JAXBElement;

@Endpoint
public class MockEndpoint {
	private static final String NAMESPACE_MOCK_URI = "http://spring.io/kingfisher/mock";

	private static final String NAMESPACE_KF_OAGIS_URI = "http://www.kingfisher.com/oagis/9";

	private static final String NAMESPACE_OAGIS_URI = "http://www.openapplications.org/oagis/9";

	private CountryRepository countryRepository;
	private CarrierRepository carrierRepository;

	@Autowired
	public MockEndpoint(CountryRepository countryRepository, CarrierRepository carrierRepository) {
		this.countryRepository = countryRepository;
		this.carrierRepository = carrierRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_MOCK_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryRequest getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return request;
	}

	@PayloadRoot(namespace = NAMESPACE_KF_OAGIS_URI, localPart = "ProcessFulfilmentSource")
	@ResponsePayload
	public AcknowledgeFulfilmentSourceType getPfs(@RequestPayload ProcessFulfilmentSourceType request) {
		AcknowledgeFulfilmentSourceType response = new AcknowledgeFulfilmentSourceType();
		//response.setCountry(countryRepository.findCarrierBooking(request.getName()));

		return response;
	}

/*	@PayloadRoot(localPart = "GetCarrierBooking", namespace = NAMESPACE_KF_OAGIS_URI)
	//@Namespace(prefix = "ns", uri = NAMESPACE_OAGIS_URI)
	//@Namespace(uri = NAMESPACE_KF_OAGIS_URI)
	@ResponsePayload
	public JAXBElement<GetCarrierBookingType> getCarrier(@RequestPayload GetCarrierBookingType request) {
		ApplicationAreaType applicationArea = request.getApplicationArea();
		//ShowCarrierBookingType response = carrierRepository.findCarrierBooking("1");
		//response.setCountry(countryRepository.findCarrierBooking(request.getName()));

		//JAXBElement<GetCarrierBookingType> CarrierBookingType
		ObjectFactory factory = new ObjectFactory();
		JAXBElement<GetCarrierBookingType> response = factory.createGetCarrierBooking(request);
		//return response.getDataArea().getShow().getResponseCriteria().get(0).getResponseExpression();
		//factory.createGetCarrierBookingType()
		return response;
	}*/


	@PayloadRoot(localPart = "GetCarrierBooking", namespace = NAMESPACE_KF_OAGIS_URI)
	@ResponsePayload
	public JAXBElement<ShowCarrierBookingType> showCarrierBooking(@RequestPayload GetCarrierBookingType request) {
		//ApplicationAreaType applicationArea = request.getApplicationArea();
		ShowCarrierBookingType showCarrierBooking = carrierRepository.findCarrierBooking("1");
		ObjectFactory factory = new ObjectFactory();
		JAXBElement<ShowCarrierBookingType> response = factory.createShowCarrierBooking(showCarrierBooking);
		return response;
	}

}
