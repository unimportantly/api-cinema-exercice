package fr.semifir.apicinema.services;

import fr.semifir.apicinema.dtos.salle.SalleDTO;
import fr.semifir.apicinema.entities.Salle;
import fr.semifir.apicinema.exceptions.NotFoundException;
import fr.semifir.apicinema.repositories.SalleRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalleService {

    SalleRepository repository;
    ModelMapper mapper;

    public SalleService(
            SalleRepository repository,
            ModelMapper mapper
            ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Retour une liste de Salle
     * @return List<Salle>
     */
    public List<SalleDTO> findAll() {
        List<SalleDTO> salleDTOS = new ArrayList<>();
        this.repository.findAll().forEach(salle -> {
            SalleDTO salleDTO = mapper.map(salle, SalleDTO.class);
            salleDTOS.add(salleDTO);
        });
        return salleDTOS;
    }

    /**
     * Je récupère un salle selon son ID
     * @param id
     * @return
     */
    public Optional<SalleDTO> findByID(String id) throws NotFoundException {
        Optional<Salle> salle = this.repository.findById(id);
        Optional<SalleDTO> salleDTO;
        if (salle.isPresent()) {
           salleDTO = Optional.of(mapper.map(salle.get(), SalleDTO.class));
        } else {
            throw new NotFoundException("Le salle n'a pas été trouvé");
        }
        return salleDTO;
    }

    /**
     * Save & update un salle
     * @param salle
     * @return
     */
    public SalleDTO save(Salle salle) {
        return mapper.map(this.repository.save(salle), SalleDTO.class);
    }

    /**
     * Je supprime mon salle
     * @param salle
     */
    public void delete(Salle salle) {
        this.repository.delete(salle);
    }
}
