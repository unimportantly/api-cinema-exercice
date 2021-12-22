package fr.semifir.apicinema.configurations;

import fr.semifir.apicinema.repositories.CinemaRepository;
import fr.semifir.apicinema.repositories.SalleRepository;
import fr.semifir.apicinema.services.CinemaService;
import fr.semifir.apicinema.services.SalleService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalleConfiguration {

    @Bean
    public SalleService salleService(SalleRepository repository, ModelMapper mapper) {
        return new SalleService(repository, mapper);
    }
}
