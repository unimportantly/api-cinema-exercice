package fr.semifir.apicinema.repositories;

import fr.semifir.apicinema.entities.Salle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalleRepository extends MongoRepository<Salle, String> { }
