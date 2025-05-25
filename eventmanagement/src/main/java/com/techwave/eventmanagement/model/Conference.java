package com.techwave.eventmanagement.model;
//hérite de Evenement
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlRootElement(name = "conference")
@XmlAccessorType(XmlAccessType.FIELD)
public class Conference extends Evenement {

    private String theme;

    @ElementCollection
    private List<String> intervenants; // Liste des noms des intervenants

    @Override
    public void afficherDetails() {
        System.out.println("Conférence sur le thème : " + theme);
        System.out.println("Intervenants : " + intervenants);
    }
}
