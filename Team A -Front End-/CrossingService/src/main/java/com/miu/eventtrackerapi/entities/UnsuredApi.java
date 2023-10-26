package com.miu.eventtrackerapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
// @Entity
public class UnsuredApi {
    // @Id
    // @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String url;
    private String extraInfo;
}