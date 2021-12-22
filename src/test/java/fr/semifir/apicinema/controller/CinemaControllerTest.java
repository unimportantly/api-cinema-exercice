package fr.semifir.apicinema.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.semifir.apicinema.controllers.CinemaController;
import fr.semifir.apicinema.dtos.cinema.CinemaDTO;
import fr.semifir.apicinema.entities.Cinema;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public CinemaDTO cinemaPatheDTO(){
        return new CinemaDTO("azmdkamzkfa", "Pathe");
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
        // create a cinema to find and pretend it's persisted
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

    @Test
    public void testUpdateCinema() throws Exception{
        // create a cinema to update later
        BDDMockito.given(cinemaService.findByID("azmdkamzkfa"))
                .willReturn(Optional.of(cinemaDTO()));
        // store the created entity as a query result
        MvcResult result = this.mockMvc.perform(get("/cinemas/azmdkamzkfa"))
                .andExpect(status().isOk())
                .andReturn();
        CinemaDTO cinemaDTO = gson().fromJson(result.getResponse().getContentAsString(),
                CinemaDTO.class);
        // change the value of an attribute to verify the update works as intended
        cinemaDTO.setNom("Pathe");
        // map the dto to a json to update it inside the mock db
        String json = gson().toJson(cinemaDTO);
        // here we tell the test that whenever it'll ask for a save with a cinemaDTO object
        // it will return the result of cinemaPatheDTO =
        // since we fake the db, for the test to be successful we have to give it
        // an object that'll be identical to the one we test against (here, names = Pathe)
        BDDMockito.when(cinemaService.save(any(CinemaDTO.class)))
                .thenReturn(cinemaPatheDTO());
        MvcResult mvcResult = this.mockMvc.perform(put("/cinemas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
        // map the result back to a dto to check if the data is consistent
        CinemaDTO finalCinemaDTO = gson().fromJson(mvcResult.getResponse().getContentAsString(),
                CinemaDTO.class);
        Assertions.assertEquals(finalCinemaDTO.getNom(), "Pathe");
    }

    @Test
    public void testDeleteCinema() throws Exception {
        // have to create a cinema to delete and store it as a json
        String body = gson().toJson(cinemaDTO());
        this.mockMvc.perform(delete("/cinemas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
        // checks if the entity has been deleted
        this.mockMvc.perform(get("/cinemas/azmdkamzkfa"))
                .andExpect(status().isNotFound());
    }

}
