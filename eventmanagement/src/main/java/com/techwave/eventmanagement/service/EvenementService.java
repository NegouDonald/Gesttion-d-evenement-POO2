package com.techwave.eventmanagement.service;

// logique métier

import com.techwave.eventmanagement.exception.CapaciteMaxAtteinteException;
import com.techwave.eventmanagement.exception.EvenementDejaExistantException;
import com.techwave.eventmanagement.model.Evenement;
import com.techwave.eventmanagement.model.Participant;
import com.techwave.eventmanagement.repository.EvenementRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.techwave.eventmanagement.repository.ParticipantRepository;

import java.util.List;
import java.util.Optional;

@Service // Marque cette classe comme un service Spring
public class EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    // Ajouter un événement
    public Evenement ajouterEvenement(Evenement evenement) {
        boolean existe = evenementRepository.findAll().stream()
                .anyMatch(e ->
                        e.getNom().equalsIgnoreCase(evenement.getNom()) &&
                                e.getDate().equals(evenement.getDate())
                );

        if (existe) {
            throw new EvenementDejaExistantException("❌ Un événement avec le même nom et la même date existe déjà !");
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
        Optional<Evenement> evenementOpt = evenementRepository.findById(id);
        evenementOpt.ifPresent(Evenement::annuler); // ⚠️ NOTIFICATION ici
        evenementRepository.deleteById(id);
    }


    // Inscription d'un participant
    public Evenement inscrireParticipant(String evenementId, Participant participant) {
        Evenement evenement = evenementRepository.findById(evenementId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        if (evenement.getParticipants().size() >= evenement.getCapaciteMax()) {
            throw new CapaciteMaxAtteinteException("❌ La capacité maximale de l’événement est atteinte !");
        }

        evenement.ajouterParticipant(participant); // ajoute dans la liste
        evenement.ajouterObservateur(participant); // pour l'observer

        return evenementRepository.save(evenement);
    }



}
