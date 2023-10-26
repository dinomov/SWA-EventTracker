package com.miu.eventtrackerapi.controllers;

import com.miu.eventtrackerapi.Service.DataApiService;
import com.miu.eventtrackerapi.Service.MessageService;
import com.miu.eventtrackerapi.Service.ServiceService;
import com.miu.eventtrackerapi.entities.DataApi;
import com.miu.eventtrackerapi.entities.Service;
import com.miu.eventtrackerapi.integration.KafkaRetriever;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService service;
    private final MessageService message;
    private final DataApiService dataApiService;

    public ServiceController(ServiceService service, MessageService message, DataApiService dataApiService) {
        this.service = service;
        this.message = message;
        this.dataApiService = dataApiService;
    }

    @GetMapping
    public Set<String> getAll(){
        return service.listTopics();
    }

    @GetMapping("/q")
    public Collection<DataApi> getAllMessagesByTopic(@RequestParam String topicName){
        return message.getMessagesFromTopic(topicName);
    }

    @PostMapping("/{topicName}")
    public ResponseEntity<String> publishMessage(@PathVariable String topicName,@RequestBody DataApi api){
        try{
            dataApiService.sendMessage(topicName, api);
            return new ResponseEntity<>("Successfully added message to topic: " + topicName, HttpStatus.OK);
        }catch(Exception e){
            System.out.println("We encountered an error: " + e);
            return new ResponseEntity<>("We encountered an error", HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/{id}")
//    Service getDataApi(@PathVariable Long id) {
//        return repo.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("service id="+id));
//    }
}
