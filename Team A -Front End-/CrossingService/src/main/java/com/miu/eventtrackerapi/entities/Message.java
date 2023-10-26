package com.miu.eventtrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("service_id")
    private Service service;
    String text;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("published_at")
    LocalDateTime publishedAt;
}
