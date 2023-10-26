package com.miu.eventtrackerapi.controllers;

import com.miu.eventtrackerapi.entities.DataApi;
import com.miu.eventtrackerapi.entities.Message;
import com.miu.eventtrackerapi.integration.KafkaRetriever;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageImpl;

import java.time.Duration;
import java.util.Arrays;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

//  @CrossOrigin
@RestController
public class DataApiController {
    private KafkaTemplate<String, DataApi> kafkaTemplate;   
    // private KafkaTemplate<String, DataApi> kafkaTemplate;


    public DataApiController(KafkaTemplate<String, DataApi> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/sources")
    public Page<DataApi> getAll(Pageable pageable){
        var ret = new KafkaRetriever<String>();
        var items = ret.getAllFromTopic("healthyapi", Duration.ofSeconds(1)).stream()
        .map(r-> {
            var api = new DataApi();
            api.setApiKey(UUID.randomUUID().toString());
            api.setUrl(r);
            return api;
        }).toList();
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()); 
        Page<DataApi> page = new PageImpl<>(items, pageRequest, items.size());
        return page;
    }

    @GetMapping("/unsuredapis")
    public List<DataApi> getAllUnsuredApis(Pageable pageable) {
        var ret = new KafkaRetriever<String>();
        var apiList = ret.getAllFromTopic("healthyapi", Duration.ofSeconds(1)).stream().toList();
        var unsuredApiList = ret.getAllFromTopic("unsuredapi", Duration.ofSeconds(1)).stream()
        .filter(r->!apiList.contains(r))
        .map(r-> {
            var api = new DataApi();
            api.setApiKey(UUID.randomUUID().toString());
            api.setUrl(r);
            return api;
        }).toList();
        return unsuredApiList;
    }

    @GetMapping("/sources/{id}")
    DataApi getDataApi(@PathVariable Long id) {
        // return repo.findById(id)
        //         .orElseThrow(() -> new EntityNotFoundException("DataApiService id="+id));
        return null;
    }

    @PutMapping("/sources/{id}/approve")
    DataApi updateDataApi(@PathVariable Long id){
        // return repo.findById(id)
        //         .map(da -> {
        //             da.setApproved(true);
        //             return repo.save(da);
        //         })
        //         .orElseThrow();
        return null;
    }

    @PutMapping("/sources")
    void updateDataApis(@PathVariable Long idList[], @RequestBody DataApi api){

//         Arrays.stream(idList)
//                 .map(repo::findById)
//                 .map(e->e.orElse(null))
//                 .filter(Objects::nonNull)
//                 .forEach(da-> {
// //                            if (api.getName() != null) da.setName(api.getName());
// //                            if (api.getUrl() != null) da.setUrl(api.getUrl());
// //                    if (api.isApproved() == null) da.setApproved(api.isApproved());
// //                            repo.save(da);
//                             System.out.println(da);
//                         }
//                 );
    }
    // @CrossOrigin
    @PostMapping("/sources")
    DataApi addDataApi(@RequestBody DataApi api) {
        // return repo.save(api);
        api.setId((long)api.getUrl().hashCode());
        kafkaTemplate.send("healthyapi", api);
        return api;
    }
//
//    @PostMapping("/sources")
//    DataApi addDataApi(@RequestBody DataApi api) {
////        return repo.save(api);
//    }

//    @PostMapping("/sources")
//    public DataApiService addApi(@RequestBody DataApiService api){
//        return repo.findByUrl(api.getUrl())
//                .orElse(()->repo.save(api));
//    }
}

