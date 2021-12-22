package fr.semifir.apicinema.dtos.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDTO {
    private String id;
    private String nom;
}
