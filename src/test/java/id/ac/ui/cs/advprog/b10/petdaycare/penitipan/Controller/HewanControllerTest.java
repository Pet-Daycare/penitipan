/*
package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Util;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller.HewanController;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan.HewanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HewanController.class)
@AutoConfigureMockMvc
class HewanControllerTest {

    private MockMvc mvc;

    @MockBean
    private HewanServiceImpl service;

    Hewan hewan;
    Object bodyContent;

    @BeforeEach
    void setUp() {
        hewan = Hewan.builder()
                .nama("Hayase Yuuka")
                .beratHewan("50Kg")
                .tipeHewan("Anaconda")
                .build();

        bodyContent = new Object() {
            public final String nama = "Hayase Yuuka";
            public final String beratHewan = "50Kg";
            public final String tipeHewan = "Anaconda";
            public final Date tanggalPenitipan = new Date();
            public final Date tanggalPengambilan = new Date();
        };
    }


    // TODO connect this test with auth microservice
    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllHewan() throws Exception {
        List<Hewan> allHewan = List.of(hewan);

        when(service.findAll()).thenReturn(allHewan);

        mvc.perform(get("/api/v1/hewan/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getAllHewan"))
                .andExpect(jsonPath("$[0].name").value(hewan.getNama()));

        verify(service, atLeastOnce()).findAll();
    }

    // TODO connect this test with auth microservice
    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetHewanById() throws Exception {
        when(service.findById(any(Integer.class))).thenReturn(hewan);

        mvc.perform(get("/api/v1/hewan/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getHewanById"))
                .andExpect(jsonPath("$.name").value(hewan.getNama()));

        verify(service, atLeastOnce()).findById(any(Integer.class));
    }

    // TODO connect this test with auth microservice
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddHewan() throws Exception {
        when(service.create(any(HewanRequest.class))).thenReturn(hewan);

        mvc.perform(post("/api/v1/hewan/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.mapToJson(bodyContent))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("addHewan"))
                .andExpect(jsonPath("$.name").value(hewan.getNama()));

        verify(service, atLeastOnce()).create(any(HewanRequest.class));
    }

    // TODO connect this test with auth microservice
    @Test
    @WithMockUser(roles = "ADMIN")
    void testPutHewan() throws Exception {
        when(service.update(any(Integer.class), any(HewanRequest.class))).thenReturn(hewan);

        mvc.perform(put("/api/v1/hewan/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.mapToJson(bodyContent))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("putHewan"))
                .andExpect(jsonPath("$.name").value(hewan.getNama()));

        verify(service, atLeastOnce()).update(any(Integer.class), any(HewanRequest.class));
    }

    // TODO connect this test with auth microservice
    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteHewan() throws Exception {
        mvc.perform(delete("/api/v1/hewan/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("deleteHewan"));

        verify(service, atLeastOnce()).delete(any(Integer.class));
    }
}
*/