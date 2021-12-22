package fr.semifir.apicinema.controller;

import com.google.gson.Gson;
import fr.semifir.apicinema.controllers.SalleController;
import fr.semifir.apicinema.dtos.salle.SalleDTO;
import fr.semifir.apicinema.entities.Cinema;
import fr.semifir.apicinema.services.CinemaService;
import fr.semifir.apicinema.services.SalleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.tags.EscapeBodyTag;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SalleController.class)
public class SalleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalleService salleService;

    @MockBean
    private CinemaService cinemaService;


    /*
    methods that'll return objects we'll transform to test the CRUD
     */

    private SalleDTO salleDTO(){
        return new SalleDTO("amozkdmoazkd", 5, 145, new Cinema("mokmoezfkomzef", "Gaumont"));
    }

    private SalleDTO otherSalleDTO(){
        return new SalleDTO("mrokgtmdokt", 6, 255, new Cinema("mgekrokgdxrgs", "UGC"));
    }

    private Gson gson(){
        return new Gson();
    }

    /*
    CRUD test
     */

    @Test
    public void testFindAllSalles() throws Exception {
        this.mockMvc.perform(get("/salles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testCreateSalle() throws Exception{
        String body = gson().toJson(salleDTO());
        this.mockMvc.perform(post("/salles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindOne() throws Exception{
        this.mockMvc.perform(post("/salles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson().toJson(salleDTO())))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/salles/"+salleDTO().getId()))
                .andExpect(status().isOk());
    }
}
