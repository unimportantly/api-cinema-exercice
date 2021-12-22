package fr.semifir.apicinema.configurations;

import fr.semifir.apicinema.repositories.CinemaRepository;
import fr.semifir.apicinema.services.CinemaService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaConfiguration {

    @Bean
    public CinemaService cinemaService(CinemaRepository repository, ModelMapper mapper) {
        return new CinemaService(repository, mapper);
    }
}
