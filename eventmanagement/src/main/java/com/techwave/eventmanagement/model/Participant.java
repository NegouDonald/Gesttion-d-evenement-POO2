package com.techwave.eventmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {

    @Id
    private String id = UUID.randomUUID().toString();

    private String nom;
    private String email;
}
