package com.recargapay.Wallet.config;

import com.recargapay.Wallet.util.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic deposit() {
        return new NewTopic(KafkaConstants.DEPOSIT_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic withdraw() {
        return new NewTopic(KafkaConstants.WITHDRAW_TOPIC, 1, (short) 1);
    }
}
