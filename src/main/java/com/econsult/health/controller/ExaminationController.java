package com.econsult.health.controller;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.dto.GrowingTendencyResponse;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.service.ExaminationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/examinations")
public class ExaminationController {

    private final ExaminationService examinationService;

    @Operation(summary = "Get all examinations",
    responses = {
            @ApiResponse(responseCode = "200", description = "List of all examinations", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExaminationDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<ExaminationDto>> getAllExaminations() {
        return ResponseEntity.ok(examinationService.getAllExaminations());
    }

    @Operation(summary = "Get an examination by id",
    responses = {
            @ApiResponse(responseCode = "200", description = "Found examination", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExaminationDto.class))),
            @ApiResponse(responseCode = "404", description = "Examination not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "examinationId", description = "The id of the examination to be retrieved.", required = true)
    })
    @GetMapping("/{examinationId}")
    public ResponseEntity<ExaminationDto> getExaminationById(@PathVariable long examinationId) {
        return ResponseEntity.ok(examinationService.getExaminationById(examinationId));
    }

    @Operation(summary = "Add an examination",
    responses= {
            @ApiResponse(responseCode = "200", description = "Examination added", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExaminationDto.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ExaminationDto to be saved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExaminationDto.class)))
    @PostMapping
    public ResponseEntity<ExaminationDto> createExamination(@RequestBody ExaminationDto examinationDto) {
        ExaminationDto savedExamination = examinationService.createExamination(examinationDto);
        return ResponseEntity.ok(savedExamination);
    }

    @Operation(summary = "Update an examination",
    responses = {
            @ApiResponse(responseCode = "200", description = "Examination updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExaminationDto.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "examinationId", description = "The id of the examination to be updated.", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ExaminationDto how to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExaminationDto.class)))
    @PutMapping("/{examinationId}")
    public ResponseEntity<ExaminationDto> updateExamination(@PathVariable Long examinationId, @RequestBody ExaminationDto examinationDto) {
        ExaminationDto updatedExaminationDto = examinationService.updateExamination(examinationId, examinationDto);
        return new ResponseEntity<>(updatedExaminationDto, HttpStatus.OK);
    }

    @Operation(summary = "Delete an examination",
    responses = {
            @ApiResponse(responseCode = "204", description = "Examination deleted")
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "examinationId", description = "The id of the examination to be deleted.", required = true)
    })
    @DeleteMapping("/{examinationId}")
    public ResponseEntity<Void> deleteExaminationById(@PathVariable Long examinationId) {
        examinationService.deleteExamination(examinationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add multiple examinations",
    responses = {
            @ApiResponse(responseCode = "200", description = "Examinations added", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExaminationDto.class))))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ExaminationDto or Dtos to be saved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExaminationDto.class)))
    @PostMapping("/multipleExaminations")
    public ResponseEntity<List<ExaminationDto>> createExaminations(@RequestBody ExaminationDto... examinationDto) {
        List<ExaminationDto> savedExaminations = examinationService.createMultipleExaminations(examinationDto);
        return ResponseEntity.ok(savedExaminations);
    }

    @Operation(summary = "Get a Patient's all results",
    responses = {
            @ApiResponse(responseCode = "200", description = "Results retireved", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "patientId", description = "The id of the Patient whose results to be retrieved.", required = true)
    })
    @GetMapping("/results/{patientId}")
    public ResponseEntity<List<String>> getPatientsResults(@PathVariable long patientId) {
        List<String> results = examinationService.getResults(patientId);
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "Get a Patient's all results by comm code",
    responses = {
            @ApiResponse(responseCode = "200", description = "Results retireved", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "patientId", description = "The id of the Patient whose results to be retrieved.", required = true),
            @Parameter(in = ParameterIn.PATH, name = "commCode", description = "The CommCode of the Examination.", required = true)
    })
    @GetMapping("/results/{patientId}/{commCode}")
    public ResponseEntity<List<String>> getPatientsResultsByCommCode(@PathVariable long patientId, @PathVariable String commCode) {
        List<String> results = examinationService.getResultsByCommCode(patientId, commCode);
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "Get a Patient's growing tendency to all kind of examination",
    responses = {
            @ApiResponse(responseCode = "200", description = "Tendencies retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GrowingTendencyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "patientId", description = "The id of the Patient whose tendencies to retrieve.", required = true)
    })
    @GetMapping("/growingTendency/{patientId}")
    public ResponseEntity<GrowingTendencyResponse> getGrowingTendencies(@PathVariable long patientId) {
        GrowingTendencyResponse tendencies = examinationService.getTendency(patientId);
        return ResponseEntity.ok(tendencies);
    }
}
