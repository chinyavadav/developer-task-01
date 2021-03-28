package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class EnquiriesServiceImplTest extends TestCase {

    private static final String partnerCode = "hot-recharge";
    private static final String msisdn = "263782135087";
    SubscriberRequest subscriberRequest;

    @Before
    public void init() {
        subscriberRequest = new SubscriberRequest();
        subscriberRequest.setId(123l);
        subscriberRequest.setRequestType("Airtime Balance Enquiry");
        subscriberRequest.setPartnerCode(partnerCode);
        subscriberRequest.setMsisdn("263782135087");
        subscriberRequest.setAmount(100);
    }

    @Test
    public void testEnquire() {
        final AirtimeBalanceResponse airtimeBalanceResponse = new AirtimeBalanceResponse();

        final INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        inBalanceResponse.setMsisdn(msisdn);
        inBalanceResponse.setAmount(100);
        inBalanceResponse.setNarrative("Airtime Purchase");

        airtimeBalanceResponse.setResponseCode(inBalanceResponse.getResponseCode());
        airtimeBalanceResponse.setNarrative(inBalanceResponse.getNarrative());
        airtimeBalanceResponse.setMsisdn(msisdn);
        airtimeBalanceResponse.setAmount(inBalanceResponse.getAmount());

        assertEquals(airtimeBalanceResponse.getResponseCode(), "200");
        assertEquals(airtimeBalanceResponse.getAmount(), 100d);
        assertEquals(airtimeBalanceResponse.getMsisdn(), "263782135087");
    }
}