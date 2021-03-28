package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.constants.SystemConstants;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnquiriesServiceImplTest extends TestCase {

    private String partnerCode, msisdn;
    private SubscriberRequest initialSubscriberRequest;

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    @Before
    public void init() {
        partnerCode = "hot-recharge";
        msisdn = "263782135087";
        initialSubscriberRequest = populate(partnerCode, msisdn);

        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setId(123l);
        subscriberRequest.setRequestType("Airtime Balance Enquiry");
        subscriberRequest.setPartnerCode(partnerCode);
        subscriberRequest.setMsisdn("263782135087");
        subscriberRequest.setAmount(100);
        when(subscriberRequestDao.save(initialSubscriberRequest)).thenReturn(subscriberRequest);

    }

    @Test
    public void testEnquire() {
        final AirtimeBalanceResponse airtimeBalanceResponse = new AirtimeBalanceResponse();
        final SubscriberRequest createdSubscriberRequest = subscriberRequestDao.save(initialSubscriberRequest);
        final INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        inBalanceResponse.setMsisdn(msisdn);
        inBalanceResponse.setAmount(100);
        inBalanceResponse.setNarrative("Airtime Purchase");
        changeSubscriberStateOnBalanceEnquiry(createdSubscriberRequest, inBalanceResponse);
        airtimeBalanceResponse.setResponseCode(inBalanceResponse.getResponseCode());
        airtimeBalanceResponse.setNarrative(inBalanceResponse.getNarrative());
        airtimeBalanceResponse.setMsisdn(msisdn);
        airtimeBalanceResponse.setAmount(inBalanceResponse.getAmount());

        assertEquals(airtimeBalanceResponse.getResponseCode(), "200");
        assertEquals(airtimeBalanceResponse.getAmount(), 100d);
        assertEquals(airtimeBalanceResponse.getMsisdn(), "263782135087");
    }

    private static void changeSubscriberStateOnBalanceEnquiry(final SubscriberRequest subscriberRequest, final INBalanceResponse inBalanceResponse) {
        final boolean isSuccessfulResponse = ResponseCode.SUCCESS.getCode().equalsIgnoreCase(inBalanceResponse.getResponseCode());
        if (!isSuccessfulResponse) {
            subscriberRequest.setStatus(SystemConstants.STATUS_FAILED);
        } else {
            subscriberRequest.setStatus(SystemConstants.STATUS_SUCCESSFUL);
            subscriberRequest.setBalanceAfter(inBalanceResponse.getAmount());
            subscriberRequest.setBalanceBefore(inBalanceResponse.getAmount());
        }
    }

    private static SubscriberRequest populate(final String partnerCode, final String msisdn) {
        final SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setRequestType(SystemConstants.REQUEST_TYPE_AIRTIME_BALANCE_ENQUIRY);
        subscriberRequest.setPartnerCode(partnerCode);
        subscriberRequest.setMsisdn(msisdn);
        return subscriberRequest;
    }
}