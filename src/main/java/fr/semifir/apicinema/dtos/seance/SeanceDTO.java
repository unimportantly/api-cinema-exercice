package fr.semifir.apicinema.dtos.seance;


import fr.semifir.apicinema.entities.Salle;
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
public class SeanceDTO {
    private String id;
    private Date date;
    private Salle salle;
}
