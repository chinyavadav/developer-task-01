package com.econetwireless.epay.api.processors.impl;

import com.econetwireless.epay.api.processors.api.ReportingProcessor;
import com.econetwireless.epay.api.rest.messages.TransactionsResponse;
import com.econetwireless.epay.business.services.api.ReportingService;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public class ReportingProcessorImpl implements ReportingProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportingProcessorImpl.class);

    private ReportingService reportingService;

    public ReportingProcessorImpl(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @Override
    public TransactionsResponse getPartnerTransactions(final String partnerCode) {
        final TransactionsResponse transactionsResponse = new TransactionsResponse();
        LOGGER.info("PANO");
        final List<SubscriberRequest> subscriberRequests = reportingService.findSubscriberRequestsByPartnerCode(partnerCode);
        transactionsResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        transactionsResponse.setNarrative("Successful search");
        transactionsResponse.setSubscriberRequests(subscriberRequests);
        transactionsResponse.setPartnerCode(partnerCode);
        LOGGER.info(transactionsResponse.toString());
        return transactionsResponse;
    }
}
