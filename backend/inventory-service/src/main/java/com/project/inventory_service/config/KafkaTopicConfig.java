package com.project.inventory_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;

@Configuration
public class KafkaTopicConfig {

    @Bean
    NewTopic createProductTopic() {
        return TopicBuilder
                .name("product-created")
                .partitions(3)
                .replicas(3)
                .build();
    }
}
