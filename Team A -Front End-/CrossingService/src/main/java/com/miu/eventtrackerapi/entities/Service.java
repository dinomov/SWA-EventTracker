package com.miu.eventtrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Service {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    String name;
    Long messageCount;
    String lastMessage;
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime lastMessageDate;
}
