package fr.semifir.apicinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salle {
    @Id
    private String id;
    private int numDeSalle;
    private int nbrPlace;
    @DBRef
    private Cinema cinema;
}
