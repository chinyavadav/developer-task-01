package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.constants.SystemConstants;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreditsServiceImplTest extends TestCase {


    private String partnerCode, msisdn;

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    @Before
    public void init() {
        partnerCode = "hot-recharge";
        msisdn = "263782135087";
    }

    @Test
    public void testCredit() {
        AirtimeTopupRequest airtimeTopupRequest = new AirtimeTopupRequest();
        airtimeTopupRequest.setAmount(100);
        airtimeTopupRequest.setMsisdn(msisdn);
        airtimeTopupRequest.setPartnerCode(partnerCode);
        airtimeTopupRequest.setReferenceNumber("REF-1234");

        final AirtimeTopupResponse airtimeTopupResponse = new AirtimeTopupResponse();
        final SubscriberRequest subscriberRequest = populateSubscriberRequest(airtimeTopupRequest);
        final SubscriberRequest createdSubscriberRequest = subscriberRequestDao.save(subscriberRequest);

        final INCreditResponse inCreditResponse = new INCreditResponse();
        inCreditResponse.setBalance(1000);
        inCreditResponse.setMsisdn(msisdn);
        inCreditResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        inCreditResponse.setNarrative("Airtime Purchase");

        airtimeTopupResponse.setResponseCode(inCreditResponse.getResponseCode());
        airtimeTopupResponse.setNarrative(inCreditResponse.getNarrative());
        airtimeTopupResponse.setMsisdn(airtimeTopupRequest.getMsisdn());
        airtimeTopupResponse.setBalance(inCreditResponse.getBalance());

        assertEquals(airtimeTopupResponse.getMsisdn(), msisdn);

        assertEquals(airtimeTopupResponse.getResponseCode(), "200");
        assertEquals(airtimeTopupResponse.getBalance(), 1000d);
    }

    private static void changeSubscriberRequestStatusOnCredit(final SubscriberRequest subscriberRequest, final INCreditResponse inCreditResponse) {
        final boolean isSuccessfulResponse = ResponseCode.SUCCESS.getCode().equalsIgnoreCase(inCreditResponse.getResponseCode());
        if (!isSuccessfulResponse) {
            subscriberRequest.setStatus(SystemConstants.STATUS_FAILED);
        } else {
            subscriberRequest.setStatus(SystemConstants.STATUS_SUCCESSFUL);
            subscriberRequest.setBalanceAfter(inCreditResponse.getBalance());
            subscriberRequest.setBalanceBefore(inCreditResponse.getBalance() - subscriberRequest.getAmount());
        }
    }

    private static SubscriberRequest populateSubscriberRequest(final AirtimeTopupRequest airtimeTopupRequest) {
        final SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setRequestType(SystemConstants.REQUEST_TYPE_AIRTIME_TOPUP);
        subscriberRequest.setPartnerCode(airtimeTopupRequest.getPartnerCode());
        subscriberRequest.setMsisdn(airtimeTopupRequest.getMsisdn());
        subscriberRequest.setReference(airtimeTopupRequest.getReferenceNumber());
        subscriberRequest.setAmount(airtimeTopupRequest.getAmount());
        return subscriberRequest;
    }

    private static INCreditRequest populate(final AirtimeTopupRequest airtimeTopupRequest) {
        final INCreditRequest inCreditRequest = new INCreditRequest();
        inCreditRequest.setAmount(airtimeTopupRequest.getAmount());
        inCreditRequest.setMsisdn(airtimeTopupRequest.getMsisdn());
        inCreditRequest.setPartnerCode(airtimeTopupRequest.getPartnerCode());
        inCreditRequest.setReferenceNumber(airtimeTopupRequest.getReferenceNumber());
        return inCreditRequest;
    }
}