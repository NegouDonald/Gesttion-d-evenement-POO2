package com.techwave.eventmanagement.controlle;

// EvenementController.java


import com.techwave.eventmanagement.model.Evenement;
import com.techwave.eventmanagement.model.Participant;
import com.techwave.eventmanagement.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Marque comme contrôleur REST
@RequestMapping("/api/evenements") // Préfixe pour toutes les routes
@CrossOrigin(origins = "*") // Autorise CORS pour le frontend React
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    // Obtenir tous les événements
    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    // Obtenir un événement par ID
    @GetMapping("/{id}")
    public Optional<Evenement> getEvenementById(@PathVariable String id) {
        return evenementService.getEvenementById(id);
    }

    // Ajouter un événement (POST)
    @PostMapping
    public Evenement ajouterEvenement(@RequestBody Evenement evenement) {
        return evenementService.ajouterEvenement(evenement);
    }


    // Supprimer un événement (DELETE)
    @DeleteMapping("/{id}")
    public void supprimerEvenement(@PathVariable String id) {
        evenementService.supprimerEvenement(id);
    }

    // Inscription d’un participant
    @PostMapping("/{id}/inscrire")
    public Evenement inscrireParticipant(
            @PathVariable String id,
            @RequestBody Participant participant) {
        return evenementService.inscrireParticipant(id, participant);
    }
}
