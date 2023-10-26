package com.miu.eventtrackerapi.integration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class KafkaRetriever<T> {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;
    public Collection<T> getAllFromTopic(String topic, Duration duration){
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, T> consumer = new KafkaConsumer<>(consumerProperties);

        consumer.subscribe(Arrays.asList(topic));
        System.out.println(consumer.assignment().size());
        consumer.seekToBeginning(consumer.assignment());

        List<T> payloads = new ArrayList<>();

        while (true) {
            ConsumerRecords<String, T> records = consumer.poll(duration);
            for (ConsumerRecord<String, T> record : records) {
                payloads.add(record.value());
            }
            if(records.isEmpty())
            break;
        }
        consumer.close();
        return payloads;
    }
}
