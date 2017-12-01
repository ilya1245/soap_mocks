package com.kingfisher.mock.pfs;

import com.kingfisher.oagis._9.CarrierBookingType;
import com.kingfisher.oagis._9.ShowCarrierBookingDataAreaType;
import com.kingfisher.oagis._9.ShowCarrierBookingType;
import org.openapplications.oagis._9.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CarrierRepository {
	private static final Map<String, ShowCarrierBookingType> carrierBooking = new HashMap<>();

	@PostConstruct
	public void initData() {
		ShowCarrierBookingType showCarrierBooking_1 = new ShowCarrierBookingType();
		ApplicationAreaType applicationArea = new ApplicationAreaType();
		applicationArea.setCreationDateTime("2019-07-19T17:23:00.000+05:30");
		IdentifierType identifierType = new IdentifierType();
		identifierType.setValue("00000016616880");
		applicationArea.setBODID(identifierType);

		ShowCarrierBookingDataAreaType bookingDataArea = new ShowCarrierBookingDataAreaType();
		ResponseActionCriteriaType responseCriteria = new  ResponseActionCriteriaType();
		ResponseExpressionType responseExpression = new ResponseExpressionType();
		responseExpression.setActionCode("Availability_1");
		responseCriteria.setResponseExpression(responseExpression);
		ShowType show = new ShowType();
		show.getResponseCriteria().add(responseCriteria);
		bookingDataArea.setShow(show);

		List<CarrierBookingType> carrierBookingList = new ArrayList<>();
		bookingDataArea.getCarrierBooking().add(new CarrierBookingType());
		showCarrierBooking_1.setApplicationArea(applicationArea);
		showCarrierBooking_1.setDataArea(bookingDataArea);



		carrierBooking.put("1", showCarrierBooking_1);


	}

	public ShowCarrierBookingType findCarrierBooking(String name) {
		//Assert.notNull(name, "The country's name must not be null");
		return carrierBooking.get(name);
	}
}
