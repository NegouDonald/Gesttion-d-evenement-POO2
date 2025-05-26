package com.techwave.eventmanagement.repository;

// ParticipantRepository.java

import com.techwave.eventmanagement.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, String> {
    Participant findByEmail(String email);


}
