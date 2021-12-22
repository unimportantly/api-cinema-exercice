package fr.semifir.apicinema.services;

import fr.semifir.apicinema.dtos.seance.SeanceDTO;
import fr.semifir.apicinema.entities.Seance;
import fr.semifir.apicinema.exceptions.NotFoundException;
import fr.semifir.apicinema.repositories.SeanceRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeanceService {

    SeanceRepository repository;
    ModelMapper mapper;

    public SeanceService(
            SeanceRepository repository,
            ModelMapper mapper
            ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Retour une liste de Seance
     * @return List<Seance>
     */
    public List<SeanceDTO> findAll() {
        List<SeanceDTO> seanceDTOS = new ArrayList<>();
        this.repository.findAll().forEach(seance -> {
            SeanceDTO seanceDTO = mapper.map(seance, SeanceDTO.class);
            seanceDTOS.add(seanceDTO);
        });
        return seanceDTOS;
    }

    /**
     * Je récupère un seance selon son ID
     * @param id
     * @return
     */
    public Optional<SeanceDTO> findByID(String id) throws NotFoundException {
        Optional<Seance> seance = this.repository.findById(id);
        Optional<SeanceDTO> seanceDTO;
        if (seance.isPresent()) {
           seanceDTO = Optional.of(mapper.map(seance.get(), SeanceDTO.class));
        } else {
            throw new NotFoundException("Le seance n'a pas été trouvé");
        }
        return seanceDTO;
    }

    /**
     * Save & update un seance
     * @param seance
     * @return
     */
    public SeanceDTO save(Seance seance) {
        return mapper.map(this.repository.save(seance), SeanceDTO.class);
    }

    /**
     * Je supprime mon seance
     * @param seance
     */
    public void delete(Seance seance) {
        this.repository.delete(seance);
    }
}
