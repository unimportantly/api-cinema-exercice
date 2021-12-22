package fr.semifir.apicinema.configurations;

import fr.semifir.apicinema.repositories.SeanceRepository;
import fr.semifir.apicinema.services.SeanceService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeanceConfiguration {

    @Bean
    public SeanceService seanceService(SeanceRepository repository, ModelMapper mapper) {
        return new SeanceService(repository, mapper);
    }
}
