package fr.semifir.apicinema.dtos.salle;

import fr.semifir.apicinema.entities.Cinema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalleDTO {
    private String id;
    private int numDeSalle;
    private int nbrPlace;
    private Cinema cinema;
}
