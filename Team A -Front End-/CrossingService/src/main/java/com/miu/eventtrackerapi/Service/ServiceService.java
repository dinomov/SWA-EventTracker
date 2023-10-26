package com.miu.eventtrackerapi.Service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Set;

@Service
public class ServiceService {
    private final KafkaAdmin kafkaAdmin;

    public ServiceService(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

    public Set<String> listTopics() {
        Properties adminProps = new Properties();
        adminProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaAdmin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));

        AdminClient adminClient = AdminClient.create(adminProps);

        ListTopicsOptions options = new ListTopicsOptions();
        options.listInternal(true);

        ListTopicsResult topics = adminClient.listTopics(options);
        try {
            return topics.names().get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to list Kafka topics", e);
        } finally {
            adminClient.close();
        }
    }
}
