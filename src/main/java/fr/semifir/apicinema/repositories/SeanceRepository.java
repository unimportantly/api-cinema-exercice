package fr.semifir.apicinema.repositories;

import fr.semifir.apicinema.entities.Salle;
import fr.semifir.apicinema.entities.Seance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeanceRepository extends MongoRepository<Seance, String> { }
