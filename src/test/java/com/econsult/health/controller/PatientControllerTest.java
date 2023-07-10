package com.econsult.health.controller;

import com.econsult.health.dto.PatientDto;
import com.econsult.health.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
class PatientControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PatientService patientService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllPatients_validRequest_returnsOKAndPatients() throws Exception {
        //given
        PatientDto patientDto1 = PatientDto.builder().id(1L).build();
        PatientDto patientDto2 = PatientDto.builder().id(2L).build();
        List<PatientDto> patientDtos = List.of(patientDto1, patientDto2);
        when(patientService.getAllPatients()).thenReturn(patientDtos);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getPatientById_patientExists_returnsOKAndPatient() throws Exception {
        //given
        PatientDto patientDto = PatientDto.builder().id(1L).build();
        when(patientService.getPatientById(1)).thenReturn(patientDto);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/patients/{patientId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createPatient_patientExists_returnsOKAndPatient() throws Exception {
        //given
        PatientDto patientDto = PatientDto.builder().id(1L).build();
        when(patientService.createPatient(patientDto)).thenReturn(patientDto);
        //when
        //then
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void updatePatient_patientExists_returnsOKAndPatient() throws Exception {
        //given
        Long patientId = 1L;
        PatientDto updatedPatientDto = PatientDto.builder().id(1L).firstName("test").build();
        when(patientService.updatePatient(1L, updatedPatientDto)).thenReturn(updatedPatientDto);
        //when
        //then
        mvc.perform(put("/api/v1/patients/{id}", patientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPatientDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deletePatient_valid_returnsOK() throws Exception {
        //given
        Long patientId = 1L;
        //when
        //then
        mvc.perform(delete("/api/v1/patients/{id}", patientId))
                .andExpect(status().isNoContent());
    }
}
