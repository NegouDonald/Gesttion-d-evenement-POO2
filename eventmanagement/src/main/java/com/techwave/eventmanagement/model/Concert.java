package com.techwave.eventmanagement.model;
//hérite de Evenement
import jakarta.persistence.Entity;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlRootElement(name = "concert")
@XmlAccessorType(XmlAccessType.FIELD)
public class Concert extends Evenement {

    private String artiste;
    private String genreMusical;

    @Override
    public void afficherDetails() {
        System.out.println("Concert de " + artiste + " - Genre : " + genreMusical);
    }
}
