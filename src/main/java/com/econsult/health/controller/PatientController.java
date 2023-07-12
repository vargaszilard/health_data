package com.econsult.health.controller;

import com.econsult.health.dto.PatientDto;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all patients", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PatientDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @Operation(summary = "Get a patient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found patient", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    })
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable long patientId) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @Operation(summary = "Add a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient added", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class)))
    })
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        PatientDto savedPatient = patientService.createPatient(patientDto);
        return ResponseEntity.ok(savedPatient);
    }

    @Operation(summary = "Update a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class)))
    })
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patientDto) {
        PatientDto updatedPatientDto = patientService.updatePatient(patientId, patientDto);
        return new ResponseEntity<>(updatedPatientDto, HttpStatus.OK);
    }

    @Operation(summary = "Delete a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient deleted")
    })
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get patient by 2T (Birth date and SSN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found patient", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundException.class)))
    })
    @GetMapping("/twoThings")
    public ResponseEntity<PatientDto> getPatientByBirthDateAndSsn(@RequestParam LocalDate birthDate, @RequestParam String ssn) {
        PatientDto patientDto = patientService.findByBirthDateAndSsn(birthDate, ssn);
        return ResponseEntity.ok(patientDto);
    }
}
