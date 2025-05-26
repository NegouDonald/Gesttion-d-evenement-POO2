package com.techwave.eventmanagement.model;

import com.techwave.eventmanagement.observer.ParticipantObserver;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Participant implements ParticipantObserver {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id ;

    private String nom;
    private String email;
    @Override
    public void recevoirNotification(String message) {
        System.out.println("🔔 Notification pour " + nom + " : " + message);
    }
}
