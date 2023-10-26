package com.miu.eventtrackerapi.Service;

import com.miu.eventtrackerapi.entities.DataApi;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataApiService {

    private final KafkaTemplate<String, DataApi> kafkaTemplate;

    public DataApiService(KafkaTemplate<String, DataApi> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String topic, DataApi api) {
        kafkaTemplate.send(topic, api);
    }
}
