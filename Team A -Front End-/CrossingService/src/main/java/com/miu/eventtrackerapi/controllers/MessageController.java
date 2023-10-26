package com.miu.eventtrackerapi.controllers;

import com.miu.eventtrackerapi.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {
    // private final MessageRepository repo;

    public MessageController(ConsumerFactory<String, String> consumerFactory) {
        // this.repo = repo;
    }

//    @GetMapping
//    public ResponseEntity<List<Message>> getAll(@RequestParam("page") int page,
//                                                @RequestParam("pageSize") int pageSize){
//
//        var items = repo.findAll();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Range", "items 0-1/"+items.size());
//        headers.add("Access-Control-Expose-Headers", "Content-Range");
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(items);
//    }

    @GetMapping
    public Page<Message> getAll(@RequestParam(required = false,name = "service_id") Long serviceId, Pageable pageable){
        // if(serviceId == null)
        //     return repo.findAll(pageable);
        // return repo.findByServiceId(serviceId, pageable);
        return null;
    }
}
