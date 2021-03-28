package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceImplTest {

    private String partnerCode;

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    @Before
    public void init() {
        partnerCode = "hot-recharge";
        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setId(123l);
        subscriberRequest.setRequestType("Airtime Balance Enquiry");
        subscriberRequest.setPartnerCode(partnerCode);
        subscriberRequest.setMsisdn("263782135087");
        subscriberRequest.setStatus("Successful");
        when(subscriberRequestDao.findByPartnerCode(partnerCode)).thenReturn(Collections.singletonList(subscriberRequest));
    }

    @Test
    public void testFindSubscriberRequestsByPartnerCode() {
        List<SubscriberRequest> subscriberRequests = subscriberRequestDao.findByPartnerCode(partnerCode);
        assertEquals(1, subscriberRequests.size());
        SubscriberRequest subscriberRequest = subscriberRequests.get(0);
        long id = subscriberRequest.getId();
        assertEquals(123, id);
        assertEquals("Airtime Balance Enquiry", subscriberRequest.getRequestType());
        assertEquals(partnerCode, subscriberRequest.getPartnerCode());
        assertEquals("263782135087", subscriberRequest.getMsisdn());
        assertEquals("Successful", subscriberRequest.getStatus());
    }
}