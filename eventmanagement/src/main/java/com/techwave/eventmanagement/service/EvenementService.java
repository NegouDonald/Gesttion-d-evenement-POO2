package com.techwave.eventmanagement.service;

// logique métier

import com.techwave.eventmanagement.exception.CapaciteMaxAtteinteException;
import com.techwave.eventmanagement.exception.EvenementDejaExistantException;
import com.techwave.eventmanagement.model.Concert;
import com.techwave.eventmanagement.model.Conference;
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

        // 🔧 Enregistre le participant avant de l'ajouter
        participant = participantRepository.save(participant);

        evenement.ajouterParticipant(participant); // ajoute dans la liste
        evenement.ajouterObservateur(participant); // pour l'observer

        return evenementRepository.save(evenement);
    }
    public Evenement desinscrireParticipant(String evenementId, String participantId) {
        Evenement evenement = evenementRepository.findById(evenementId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant non trouvé"));

        evenement.getParticipants().removeIf(p -> p.getId().equals(participantId));
        return evenementRepository.save(evenement);
    }

    public List<Evenement> rechercherParNom(String query) {
        return evenementRepository.findByNomContainingIgnoreCase(query);
    }

    public Evenement modifierEvenement(String id, Evenement modifie) {
        Evenement original = evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        original.setNom(modifie.getNom());
        original.setDate(modifie.getDate());
        original.setLieu(modifie.getLieu());
        original.setCapaciteMax(modifie.getCapaciteMax());

        if (original instanceof Conference && modifie instanceof Conference) {
            ((Conference) original).setTheme(((Conference) modifie).getTheme());
            ((Conference) original).setIntervenants(((Conference) modifie).getIntervenants());
        }

        if (original instanceof Concert && modifie instanceof Concert) {
            ((Concert) original).setArtiste(((Concert) modifie).getArtiste());
            ((Concert) original).setGenreMusical(((Concert) modifie).getGenreMusical());
        }

        return evenementRepository.save(original);
    }




}
