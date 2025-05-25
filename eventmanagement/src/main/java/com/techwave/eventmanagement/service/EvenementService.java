package com.techwave.eventmanagement.service;

// logique métier

import com.techwave.eventmanagement.model.Evenement;
import com.techwave.eventmanagement.model.Participant;
import com.techwave.eventmanagement.repository.EvenementRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service // Marque cette classe comme un service Spring
public class EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    // Ajouter un événement
    public Evenement ajouterEvenement(@Valid @RequestBody Evenement evenement) {
        System.out.println("------ EVENEMENT RECU ------");
        System.out.println(evenement);

        if (evenement.getDate() == null) {
            System.out.println("⚠️  ATTENTION : La date est NULL");
        }

        return evenementRepository.save(evenement);
    }


    // Obtenir tous les événements
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();

    }

    // Rechercher un événement par ID
    public Optional<Evenement> getEvenementById(String id) {
        return evenementRepository.findById(id);
    }

    // Supprimer un événement
    public void supprimerEvenement(String id) {
        evenementRepository.deleteById(id);
    }

    // Inscription d'un participant
    public Evenement inscrireParticipant(String evenementId, Participant participant) {
        Evenement evenement = evenementRepository.findById(evenementId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        evenement.ajouterParticipant(participant);
        return evenementRepository.save(evenement); // mise à jour
    }
}
