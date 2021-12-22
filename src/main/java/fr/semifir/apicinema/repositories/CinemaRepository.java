package fr.semifir.apicinema.repositories;

import fr.semifir.apicinema.entities.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CinemaRepository extends MongoRepository<Cinema, String> { }
