package com.techwave.eventmanagement.controlle;


import com.techwave.eventmanagement.service.FichierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fichier")
@CrossOrigin(origins = "*")
public class FichierController {

    @Autowired
    private FichierService fichierService;

    // Export JSON
    @GetMapping("/export/json")
    public String exportJson() throws Exception {
        fichierService.exportToJson();
        return "Fichier JSON exporté avec succès.";
    }

    // Import JSON
    @GetMapping("/import/json")
    public String importJson() throws Exception {
        fichierService.importFromJson();
        return "Fichier JSON importé avec succès.";
    }

    // Export XML
    @GetMapping("/export/xml")
    public String exportXml() throws Exception {
        fichierService.exportToXml();
        return "Fichier XML exporté avec succès.";
    }

    // Import XML
    @GetMapping("/import/xml")
    public String importXml() throws Exception {
        fichierService.importFromXml();
        return "Fichier XML importé avec succès.";
    }
}
