package com.techwave.eventmanagement.service;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.techwave.eventmanagement.model.Concert;
import com.techwave.eventmanagement.model.Conference;
import com.techwave.eventmanagement.model.Evenement;
import com.techwave.eventmanagement.repository.EvenementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.*;

import jakarta.xml.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

// Service pour sérialisation JSON/XML
@Service
public class FichierService {

    @Autowired
    private EvenementRepository evenementRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final String JSON_FILE = "evenements.json";
    private final String XML_FILE = "evenements.xml";

    // Classe conteneur pour XML
    @XmlRootElement(name = "evenements")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlSeeAlso({Conference.class, Concert.class})
    public static class EvenementListWrapper {

        @XmlElements({
                @XmlElement(name = "conference", type = Conference.class),
                @XmlElement(name = "concert", type = Concert.class)
        })
        public List<Evenement> evenements;

        public EvenementListWrapper() {}
        public EvenementListWrapper(List<Evenement> evenements) {
            this.evenements = evenements;
        }
    }


    // Export JSON
    public void exportToJson() throws Exception {
        List<Evenement> evenements = evenementRepository.findAll();
        objectMapper.writeValue(new File(JSON_FILE), evenements);
    }

    // Import JSON
    public void importFromJson() throws Exception {
        File file = new File(JSON_FILE);
        Evenement[] evenements = objectMapper.readValue(file, Evenement[].class);
        for (Evenement e : evenements) {
            evenementRepository.save(e);
        }
    }

    // Export XML
    public void exportToXml() throws Exception {
        List<Evenement> evenements = evenementRepository.findAll();
        JAXBContext context = JAXBContext.newInstance(EvenementListWrapper.class, Evenement.class, Conference.class,
                Concert.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new EvenementListWrapper(evenements), new File(XML_FILE));
    }

    // Import XML
    public void importFromXml() throws Exception {
        JAXBContext context = JAXBContext.newInstance(EvenementListWrapper.class, Conference.class, Concert.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmlFile = new File("evenements.xml");
        EvenementListWrapper wrapper = (EvenementListWrapper) unmarshaller.unmarshal(xmlFile);

        if (wrapper == null || wrapper.evenements == null) {
            System.out.println("⚠️ Aucune donnée trouvée dans le fichier XML.");
            return;
        }

        for (Evenement e : wrapper.evenements) {
            evenementRepository.save(e);
        }
    }

}
