package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.services.api.ReportingService;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public class ReportingServiceImpl implements ReportingService {
    Logger logger = LoggerFactory.getLogger(ReportingServiceImpl.class);

    private SubscriberRequestDao subscriberRequestDao;

    public ReportingServiceImpl(SubscriberRequestDao subscriberRequestDao) {
        this.subscriberRequestDao = subscriberRequestDao;
    }

    @Override
    public List<SubscriberRequest> findSubscriberRequestsByPartnerCode(String partnerCode) {
        return subscriberRequestDao.findByPartnerCode(partnerCode);
    }
}
