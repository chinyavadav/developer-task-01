package com.econetwireless.epay.business.integrations.impl;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.utils.MessageConverters;
import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.CreditRequest;
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
        System.out.println(intelligentNetworkService);
        try {
            return MessageConverters.convert(intelligentNetworkService.enquireBalance(partnerCode, msisdn));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public INCreditResponse creditSubscriberAccount(final INCreditRequest inCreditRequest) {
        final CreditRequest creditRequest = MessageConverters.convert(inCreditRequest);
        return MessageConverters.convert(intelligentNetworkService.creditSubscriberAccount(creditRequest));
    }
}
