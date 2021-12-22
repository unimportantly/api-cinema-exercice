package fr.semifir.apicinema.configurations;

import fr.semifir.apicinema.repositories.FilmRepository;
import fr.semifir.apicinema.repositories.SeanceRepository;
import fr.semifir.apicinema.services.FilmService;
import fr.semifir.apicinema.services.SeanceService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilmConfiguration {

    @Bean
    public FilmService filmService(FilmRepository repository, ModelMapper mapper) {
        return new FilmService(repository, mapper);
    }
}
