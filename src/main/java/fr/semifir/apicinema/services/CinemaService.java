package fr.semifir.apicinema.services;

import fr.semifir.apicinema.dtos.cinema.CinemaDTO;
import fr.semifir.apicinema.entities.Cinema;
import fr.semifir.apicinema.exceptions.NotFoundException;
import fr.semifir.apicinema.repositories.CinemaRepository;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CinemaService {

    CinemaRepository repository;
    ModelMapper mapper;

    public CinemaService(
            CinemaRepository repository,
            ModelMapper mapper
            ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Retour une liste de Cinema
     * @return List<Cinema>
     */
    public List<CinemaDTO> findAll() {
        // Je crée une liste vide de Cinema DTO
        List<CinemaDTO> cinemaDTOS = new ArrayList<>();
        // J'appelle mon repo pour récupérer mes cinémas
        // Je boucle directement avec un Foreach le retour de mon repo
        // J'utlise une lambda
        this.repository.findAll().forEach(cinema -> {
            // Je map l'entité Cinema en CinemaDTO
            CinemaDTO cinemaDTO = mapper.map(cinema, CinemaDTO.class);
            // Je l'ajoute à mon tableau
            cinemaDTOS.add(cinemaDTO);
        });
        return cinemaDTOS;
    }

    /**
     * Je récupère un cinema selon son ID
     * @param id
     * @return
     */
    public Optional<CinemaDTO> findByID(String id) throws NotFoundException {
        Optional<Cinema> cinema = this.repository.findById(id);
        Optional<CinemaDTO> cinemaDTO;
        if (cinema.isPresent()) {
           cinemaDTO = Optional.of(mapper.map(cinema.get(), CinemaDTO.class));
        } else {
            throw new NotFoundException("Le cinema n'a pas été trouvé");
        }
        return cinemaDTO;
    }

    /**
     * Save & update un cinema
     * @param cinema
     * @return
     */
    public CinemaDTO save(Cinema cinema) {
        return mapper.map(this.repository.save(cinema), CinemaDTO.class);
    }

    /**
     * Je supprime mon cinema
     * @param cinema
     */
    public void delete(Cinema cinema) {
        this.repository.delete(cinema);
    }
}
