package com.techwave.eventmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organisateur extends Participant {

    @OneToMany
    private List<Evenement> evenementsOrganises = new ArrayList<>(); // Événements qu’il a créés
}
