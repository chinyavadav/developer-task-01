package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.dao.requestpartner.api.RequestPartnerDao;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.RequestPartner;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.execeptions.EpayException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartnerCodeValidatorImplTest {

    private String partnerCode;

    @Mock
    private RequestPartnerDao requestPartnerDao;

    @Before
    public void init() {
        partnerCode = "hot-recharge";
        RequestPartner requestPartner = new RequestPartner();
        requestPartner.setId(123l);
        requestPartner.setCode(partnerCode);
        requestPartner.setName("Hot Recharge");
        requestPartner.setDescription("HOT RECHARGE PLATFORM");
        when(requestPartnerDao.findByCode(partnerCode)).thenReturn(requestPartner);
    }

    @Test
    public void testValidatePartnerCode() {
        assertTrue(requestPartnerDao.findByCode(partnerCode) != null);
    }
}