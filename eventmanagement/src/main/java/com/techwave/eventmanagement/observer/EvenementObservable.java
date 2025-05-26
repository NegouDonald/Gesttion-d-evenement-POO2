package com.techwave.eventmanagement.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class EvenementObservable {
    private final List<ParticipantObserver> observateurs = new ArrayList<>();

    public void ajouterObservateur(ParticipantObserver p) {
        observateurs.add(p);
    }

    public void supprimerObservateur(ParticipantObserver p) {
        observateurs.remove(p);
    }

    public void notifierObservateurs(String message) {
        for (ParticipantObserver p : observateurs) {
            p.recevoirNotification(message);
        }
    }
}
