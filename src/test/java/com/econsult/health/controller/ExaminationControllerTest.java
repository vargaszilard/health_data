package com.econsult.health.controller;

import com.econsult.health.dto.DateResult;
import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.dto.GrowingTendencyResponse;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.service.ExaminationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExaminationController.class)
@AutoConfigureMockMvc(addFilters = false)
class ExaminationControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ExaminationService examinationService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllExaminations_validRequest_returnsOKAndExaminations() throws Exception {
        //given
        ExaminationDto examinationDto1 = ExaminationDto.builder().id(1L).build();
        ExaminationDto examinationDto2 = ExaminationDto.builder().id(2L).build();
        List<ExaminationDto> examinationDtos = List.of(examinationDto1, examinationDto2);
        when(examinationService.getAllExaminations()).thenReturn(examinationDtos);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/examinations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getExaminationById_examinationExists_returnsOKAndExamination() throws Exception {
        //given
        ExaminationDto examinationDto = ExaminationDto.builder().id(1L).build();
        when(examinationService.getExaminationById(1)).thenReturn(examinationDto);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/examinations/{examinationId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getExaminationById_examinationNotExists_returnsOKAndExamination() throws Exception {
        //given
        when(examinationService.getExaminationById(1)).thenThrow(new EntityNotFoundException("Entity not found"));
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/examinations/{examinationId}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void createExamination_examinationExists_returnsOKAndExamination() throws Exception {
        //given
        ExaminationDto examinationDto = ExaminationDto.builder().id(1L).build();
        when(examinationService.createExamination(examinationDto)).thenReturn(examinationDto);
        //when
        //then
        mvc.perform(post("/api/v1/examinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(examinationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void updateExamination_examinationExists_returnsOKAndExamination() throws Exception {
        //given
        Long examinationId = 1L;
        ExaminationDto updatedExaminationDto = ExaminationDto.builder().id(1L).name("test").build();
        when(examinationService.updateExamination(1L, updatedExaminationDto)).thenReturn(updatedExaminationDto);
        //when
        //then
        mvc.perform(put("/api/v1/examinations/{id}", examinationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedExaminationDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteExamination_valid_returnsOK() throws Exception {
        //given
        Long examinationId = 1L;
        //when
        //then
        mvc.perform(delete("/api/v1/examinations/{id}", examinationId))
                .andExpect(status().isNoContent());
    }

    @Test
    void createExaminations_validExaminations_returnsOkAndExaminations() throws Exception {
        //given
        ExaminationDto examinationDto1 = ExaminationDto.builder().id(1L).patientId(1L).build();
        ExaminationDto examinationDto2 = ExaminationDto.builder().id(2L).patientId(1L).build();
        ExaminationDto[] examinationDtos = {examinationDto1, examinationDto2};
        List<ExaminationDto> examinationDtoList = List.of(examinationDto1, examinationDto2);
        when(examinationService.createMultipleExaminations(examinationDtos)).thenReturn(examinationDtoList);
        //when
        //then
        mvc.perform(post("/api/v1/examinations/multipleExaminations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(examinationDtos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void getPatientsResults_existingPatient_returnsOkAndResults() throws Exception {
        //given
        List<String> excepted = List.of("10", "12");
        long id = 1L;
        when(examinationService.getResults(id)).thenReturn(excepted);
        //when
        //then
        mvc.perform(get("/api/v1/examinations/results/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("10"))
                .andExpect(jsonPath("$[1]").value("12"));
    }

    @Test
    void getPatientsResultsByCommCode_existingPatient_returnsOkAndResults() throws Exception {
        //given
        List<String> excepted = List.of("10", "12");
        long id = 1L;
        String commCode = "com";
        when(examinationService.getResultsByCommCode(id, commCode)).thenReturn(excepted);
        //when
        //then
        mvc.perform(get("/api/v1/examinations/results/{id}/{commCode}", id, commCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("10"))
                .andExpect(jsonPath("$[1]").value("12"));
    }

    @Test
    void getGrowingTendencies_existingPatient_returnsOkAndResult() throws Exception {
        //given
        long id = 1L;
        List<DateResult> list = List.of(
                new DateResult(LocalDateTime.of(2023, 1, 1, 1, 1, 1), "10"),
                new DateResult(LocalDateTime.of(2023, 1, 2, 1, 1, 1), "12")
        );
        Map<String, List<DateResult>> map = new HashMap<>();
        map.put("NA", list);
        GrowingTendencyResponse growingTendencyResponse = new GrowingTendencyResponse(id, map);
        when(examinationService.getTendency(id)).thenReturn(growingTendencyResponse);
        //when
        mvc.perform(get("/api/v1/examinations/growingTendency/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value(1L));
    }

}
