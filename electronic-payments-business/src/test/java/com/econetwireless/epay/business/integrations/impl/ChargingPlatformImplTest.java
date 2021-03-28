package com.econetwireless.epay.business.integrations.impl;

import com.econetwireless.epay.business.utils.MessageConverters;
import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.CreditRequest;
import com.econetwireless.in.webservice.CreditResponse;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ChargingPlatformImplTest {

    private String partnerCode, msisdn;

    @Before
    public void init() {
        partnerCode = "hot-recharge";
        msisdn = "263782135087";
    }

    @Test
    public void testEnquireBalance() {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setMsisdn(msisdn);
        balanceResponse.setAmount(100);
        balanceResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        balanceResponse.setNarrative("Airtime Purchase");

        INBalanceResponse inBalanceResponse = MessageConverters.convert(balanceResponse);
        assertEquals(msisdn, inBalanceResponse.getMsisdn());
        assertEquals("200", inBalanceResponse.getResponseCode());
    }

    public void testCreditSubscriberAccount() {
        INCreditRequest inCreditRequest = new INCreditRequest();
        inCreditRequest.setPartnerCode(partnerCode);
        inCreditRequest.setAmount(100);
        inCreditRequest.setMsisdn(msisdn);
        inCreditRequest.setReferenceNumber("REF-1234");

        final CreditRequest creditRequest = MessageConverters.convert(inCreditRequest);

        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        creditResponse.setBalance(1000);
        creditResponse.setMsisdn(inCreditRequest.getMsisdn());
        creditResponse.setNarrative(inCreditRequest.getReferenceNumber());

        assertEquals(creditRequest.getMsisdn(), msisdn);
        assertEquals(creditRequest.getPartnerCode(), partnerCode);
        assertEquals(creditRequest.getAmount(), inCreditRequest.getAmount());
    }
}