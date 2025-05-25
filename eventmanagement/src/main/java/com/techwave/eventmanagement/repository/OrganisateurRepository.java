package com.techwave.eventmanagement.repository;
// OrganisateurRepository.java


import com.techwave.eventmanagement.model.Organisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisateurRepository extends JpaRepository<Organisateur, String> {
}
