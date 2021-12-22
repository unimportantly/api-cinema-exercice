package fr.semifir.apicinema.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    private String id;
    private String nom;
    private Float duree;
    @DBRef
    private Seance seance;
}
