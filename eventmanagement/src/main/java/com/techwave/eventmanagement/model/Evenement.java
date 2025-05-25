package com.techwave.eventmanagement.model;
//classe abstraite


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Désérialisation + sérialisation polymorphe
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type", // Champ visible dans le JSON
        visible = true // nécessaire pour sérialiser et désérialiser
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Conference.class, name = "conference"),
        @JsonSubTypes.Type(value = Concert.class, name = "concert")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Conference.class, Concert.class})
public abstract class Evenement {

    @Id
    private String id = UUID.randomUUID().toString();

    private String nom;
    @NotNull(message = "La date est obligatoire")
    private LocalDateTime date;
    private String lieu;
    private int capaciteMax;

    @ManyToMany
    private List<Participant> participants = new ArrayList<>();

    public void ajouterParticipant(Participant participant) {
        if (participants.size() >= capaciteMax) {
            throw new RuntimeException("Capacité maximale atteinte !");
        }
        participants.add(participant);
    }

    public void annuler() {
        System.out.println("Événement annulé : " + nom);
    }

    public abstract void afficherDetails();
}
