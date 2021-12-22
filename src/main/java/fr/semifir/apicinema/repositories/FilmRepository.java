package fr.semifir.apicinema.repositories;

import fr.semifir.apicinema.entities.Film;
import fr.semifir.apicinema.entities.Seance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmRepository extends MongoRepository<Film, String> { }
