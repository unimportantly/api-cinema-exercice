package fr.semifir.apicinema.controllers;

import fr.semifir.apicinema.dtos.cinema.CinemaDTO;
import fr.semifir.apicinema.entities.Cinema;
import fr.semifir.apicinema.exceptions.NotFoundException;
import fr.semifir.apicinema.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    CinemaService service;

    @GetMapping
    public List<CinemaDTO> findAll() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<CinemaDTO> findById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.service.findByID(id).get());
        } catch (NoSuchElementException e) {
           return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<CinemaDTO> save(@RequestBody CinemaDTO cinema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cinema));
    }

    @PutMapping
    public CinemaDTO update(@RequestBody CinemaDTO cinema) {
        return this.service.save(cinema);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestBody Cinema cinema) {
        this.service.delete(cinema);
        return ResponseEntity.ok(true);
    }
}
