package com.techwave.eventmanagement.repository;

// EvenementRepository.java

import com.techwave.eventmanagement.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, String> {
    // Recherche personnalisée possible ici

}
