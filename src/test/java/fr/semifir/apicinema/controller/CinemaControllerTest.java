package fr.semifir.apicinema.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.semifir.apicinema.controllers.CinemaController;
import fr.semifir.apicinema.dtos.cinema.CinemaDTO;
import fr.semifir.apicinema.services.CinemaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CinemaController.class)
public class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaService cinemaService;

    public CinemaDTO cinemaDTO(){
        return new CinemaDTO("azmdkamzkfa", "gaumont");
    }

    public Gson gson(){
        return new GsonBuilder().create();
    }

    @Test
    public void testFindAllCinemas() throws Exception {
        this.mockMvc.perform(get("/cinemas"))
                .andExpect(status().isOk())
                // tells the test the list will be empty since there are no cinemas created yet
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testCreateOneCinema() throws Exception{
        CinemaDTO cinemaDTO = cinemaDTO();
        Gson json = new GsonBuilder().create();
        String string = json.toJson(cinemaDTO);
        this.mockMvc.perform(post("/cinemas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(string))
                /*
                asserts that the response status is 201
                so either have to change it to ok (200) or add a response entity
                returning the proper status in the controller
                 */
                .andExpect(status().isCreated());
    }

    @Test
    public void testFindOneCinema() throws Exception{
        // create a cinema to find and persists it
        BDDMockito.given(cinemaService.findByID("azmdkamzkfa"))
                .willReturn(Optional.of(cinemaDTO()));
        // verify something was created and stores the result of the request
        MvcResult result = this.mockMvc.perform(get("/cinemas/azmdkamzkfa"))
                .andExpect(status().isOk())
                .andReturn();
        // map the result into a dto so we can compare the cinemaDTO we tried to
        // persist with the return we got
        CinemaDTO cinemaDTO = gson().fromJson(result.getResponse().getContentAsString(),
                CinemaDTO.class);
        Assertions.assertEquals(cinemaDTO.getNom(), "gaumont");
    }
}
