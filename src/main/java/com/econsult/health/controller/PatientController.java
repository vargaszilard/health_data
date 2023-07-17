package com.econsult.health.controller;

import com.econsult.health.dto.PatientDto;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.service.PatientService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Get all patients",
    responses = {
            @ApiResponse(responseCode = "200", description = "List of all patients", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PatientDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @Operation(summary = "Get a patient by id",
    responses = {
            @ApiResponse(responseCode = "200", description = "Found patient", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "patientId", description = "The id of the Patient to be retrieved.", required = true)
    })
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable long patientId) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @Operation(summary = "Add a patient",
    responses = {
            @ApiResponse(responseCode = "200", description = "Patient added", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "PatientDto to be saved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class)))
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        PatientDto savedPatient = patientService.createPatient(patientDto);
        return ResponseEntity.ok(savedPatient);
    }

    @Operation(summary = "Update a patient",
    responses = {
            @ApiResponse(responseCode = "200", description = "Patient updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "patientId", description = "The id of the Patient to be updated.", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "PatientDto how to be updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class)))
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patientDto) {
        PatientDto updatedPatientDto = patientService.updatePatient(patientId, patientDto);
        return new ResponseEntity<>(updatedPatientDto, HttpStatus.OK);
    }

    @Operation(summary = "Delete a patient",
    responses = {
            @ApiResponse(responseCode = "200", description = "Patient deleted")
    },
    parameters = {
            @Parameter(in = ParameterIn.PATH, name = "patientId", description = "The id of the Patient to be deleted.", required = true)
    })
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get patient by 2T (BirthDate and SSN)",
    responses = {
            @ApiResponse(responseCode = "200", description = "found patient", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    },
    parameters = {
            @Parameter(in = ParameterIn.QUERY, name = "birthDate", description = "The BirthDate of the Patient to be retrieved.", required = true),
            @Parameter(in = ParameterIn.QUERY, name = "ssn", description = "The SSN of the Patient to be retrieved.", required = true)
    })
    @GetMapping("/twoThings")
    public ResponseEntity<PatientDto> getPatientByBirthDateAndSsn(@RequestParam LocalDate birthDate, @RequestParam String ssn) {
        PatientDto patientDto = patientService.findByBirthDateAndSsn(birthDate, ssn);
        return ResponseEntity.ok(patientDto);
    }
}
