package fr.semifir.apicinema.dtos.film;


import fr.semifir.apicinema.entities.Seance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private String id;
    private String nom;
    private Float duree;
    private Seance seance;
}
