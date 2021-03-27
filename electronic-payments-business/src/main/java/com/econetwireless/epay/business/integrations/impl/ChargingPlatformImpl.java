package com.econetwireless.epay.business.integrations.impl;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.utils.MessageConverters;
import com.econetwireless.in.webservice.CreditRequest;
import com.econetwireless.in.webservice.CreditResponse;
import com.econetwireless.in.webservice.IntelligentNetworkService;
import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;

/**
 * Created by tnyamakura on 17/3/2017.
 */
public class ChargingPlatformImpl implements ChargingPlatform {

    private IntelligentNetworkService intelligentNetworkService;

    public ChargingPlatformImpl(IntelligentNetworkService intelligentNetworkService) {
        this.intelligentNetworkService = intelligentNetworkService;
    }

    @Override
    public INBalanceResponse enquireBalance(final String partnerCode, final String msisdn) {
        return MessageConverters.convert(intelligentNetworkService.enquireBalance(partnerCode, msisdn));
    }

    @Override
    public INCreditResponse creditSubscriberAccount(final INCreditRequest inCreditRequest) {
        final CreditRequest creditRequest = MessageConverters.convert(inCreditRequest);
        System.out.println(creditRequest.getAmount());
        System.out.println(creditRequest.getMsisdn());
        System.out.println(creditRequest.getPartnerCode());
        System.out.println(creditRequest.getReferenceNumber());
        System.out.println("pano00");
        CreditResponse x = intelligentNetworkService.creditSubscriberAccount(creditRequest);
        System.out.println("pano");
        System.out.println(x);
        INCreditResponse y = MessageConverters.convert(x);
        System.out.println(y);
        return y;
    }
}
