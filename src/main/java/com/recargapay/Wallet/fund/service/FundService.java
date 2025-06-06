package com.recargapay.Wallet.fund.service;

import com.recargapay.Wallet.fund.dto.FundRequest;
import com.recargapay.Wallet.util.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class FundService {

    private final KafkaTemplate<String, FundRequest> kafkaTemplate;

    public void deposit(FundRequest fundRequest) {
        log.info("Send request for deposit to Kafka");
        kafkaTemplate.send(KafkaConstants.DEPOSIT_TOPIC, fundRequest);
    }

}
