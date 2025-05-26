package com.techwave.eventmanagement.repository;

// EvenementRepository.java
// Importation de la classe Evenement, qui est l'entité liée à cette interface
import com.techwave.eventmanagement.model.Evenement;
// Importation de JpaRepository, qui permet d’hériter des opérations de base sur la base de données
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// Déclaration de l'interface EvenementRepository
// Elle hérite de JpaRepository avec comme type :
// - Evenement : l'entité à gérer
// 🔍 Méthode personnalisée Spring Data JPA
// Cette méthode permet de chercher tous les événements dont le nom contient une certaine chaîne
// en ignorant la casse (majuscule/minuscule)
public interface EvenementRepository extends JpaRepository<Evenement, String> {
    // Recherche personnalisée possible ici

    List<Evenement> findByNomContainingIgnoreCase(String nom);


}
