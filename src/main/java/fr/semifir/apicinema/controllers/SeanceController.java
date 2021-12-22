package fr.semifir.apicinema.controllers;

import fr.semifir.apicinema.dtos.seance.SeanceDTO;
import fr.semifir.apicinema.dtos.seance.SeanceDTO;
import fr.semifir.apicinema.entities.Seance;
import fr.semifir.apicinema.exceptions.NotFoundException;
import fr.semifir.apicinema.services.SalleService;
import fr.semifir.apicinema.services.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("seances")
public class SeanceController {

    @Autowired
    SeanceService service;

    @GetMapping
    public List<SeanceDTO> findAll() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<SeanceDTO> findById(@PathVariable String id) {
        Optional<SeanceDTO> SeanceDTO = null;
        try {
            SeanceDTO = this.service.findByID(id);
        } catch (NotFoundException e) {
           return ResponseEntity.notFound().header(e.getMessage()).build();
        }
        return ResponseEntity.ok(SeanceDTO.get());
    }

    @PostMapping
    public SeanceDTO save(@RequestBody Seance seance) {
        return this.service.save(seance);
    }

    @PutMapping
    public SeanceDTO update(@RequestBody Seance seance) {
        return this.service.save(seance);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestBody Seance seance) {
        this.service.delete(seance);
        return ResponseEntity.ok(true);
    }
}
