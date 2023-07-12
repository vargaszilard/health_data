package com.econsult.health.service;

import com.econsult.health.dto.PatientDto;
import com.econsult.health.entity.Patient;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.mapper.PatientMapper;
import com.econsult.health.repository.PatientRepository;
import com.econsult.health.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientService;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientMapper patientMapper;

    @Test
    void getAllPatients_patientsExist_returnPatientDtos() {
        //given
        Patient patient1 = createPatient(1L, "testName", LocalDate.of(2000, 1, 1), "NA");
        Patient patient2 = createPatient(2L, "testName2", LocalDate.of(2000, 1, 1), "NA");
        PatientDto patientDto1 = createPatientDto(1L, "testName", LocalDate.of(2000, 1, 1), "NA");
        PatientDto patientDto2 = createPatientDto(2L, "testName2", LocalDate.of(2000, 1, 1), "NA");
        List<Patient> patients = List.of(patient1, patient2);
        List<PatientDto> patientDtos = List.of(patientDto1, patientDto2);
        when(patientRepository.findAll()).thenReturn(patients);
        when(patientMapper.toPatientDtoList(patients)).thenReturn(patientDtos);
        //when
        List<PatientDto> result = patientService.getAllPatients();
        //then
        assertTrue(result.contains(patientDto1));
        assertTrue(result.contains(patientDto2));
    }

    @Test
    void findById_patientExists_returnPatient() {
        //given
        Patient expectedPatient = createPatient(1L, "testName", LocalDate.of(2000, 1, 1), "NA");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(expectedPatient));
        //when
        Patient result = patientService.findById(1L);
        //then
        assertEquals(expectedPatient, result);
    }

    @Test
    void findById_idDoesNotExist_throwsException() {
        //given
        //when
        //then
        assertThatThrownBy(() -> patientService.findById(1L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void getPatientById_idExists_returnPatientDto() {
        //given
        Patient patient = createPatient(1L, "testName", LocalDate.of(2000, 1, 1), "NA");
        PatientDto expected = createPatientDto(1L, "testName", LocalDate.of(2000, 1, 1), "NA");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientMapper.toPatientDto(patient)).thenReturn(expected);
        //when
        PatientDto result = patientService.getPatientById(1L);
        //then
        assertEquals(expected, result);
    }

    @Test
    void createPatient_validPatient_returnPatientDto() {
        //given
        Patient patient = createPatient(1L, "testName", LocalDate.of(2000, 1, 1), "NA");
        PatientDto expected = createPatientDto(1L, "testName", LocalDate.of(2000, 1, 1), "NA");
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toPatient(expected)).thenReturn(patient);
        when(patientMapper.toPatientDto(patient)).thenReturn(expected);
        //when
        PatientDto result = patientService.createPatient(expected);
        //then
        assertEquals(expected, result);
    }

    @Test
    void updatePatient_validPatient_returnUpdatedPatient() {
        //given
        long id = 1L;
        PatientDto updatedPatientDto = createPatientDto(id, "testNameUpdated", LocalDate.of(2000, 1, 1), "NA");
        Patient updatedPatient = createPatient(id, "testNameUpdated", LocalDate.of(2000, 1, 1), "NA");
        Patient patient = createPatient(id, "testName", LocalDate.of(2000, 1, 1), "NA");
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(patientRepository.save(updatedPatient)).thenReturn(updatedPatient);
        when(patientMapper.toPatientDto(updatedPatient)).thenReturn(updatedPatientDto);
        //when
        PatientDto result = patientService.updatePatient(id, updatedPatientDto);
        //then
        assertEquals(updatedPatientDto, result);
    }

    @Test
    void findByBirthDateAndSsn_validParams_returnPatientDto() {
        //given
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        String ssn = "NA";
        Patient patient = createPatient(1L, "testName", birthDate, ssn);
        PatientDto patientDto = createPatientDto(1L, "testName", birthDate, ssn);
        when(patientRepository.findByBirthDateAndSsn(birthDate, ssn)).thenReturn(Optional.of(patient));
        when(patientMapper.toPatientDto(patient)).thenReturn(patientDto);
        //when
        PatientDto result = patientService.findByBirthDateAndSsn(birthDate, ssn);
        //then
        assertEquals(patientDto, result);
    }

    @Test
    void findByBirthDateAndSsn_patientNotFound_throwsException() {
        //given
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        String ssn = "NA";
        //when
        //then
        assertThatThrownBy(() -> patientService.findByBirthDateAndSsn(birthDate, ssn))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void existPatientById_patientExist_returnTrue() {
        //given
        when(patientRepository.existsById(1L)).thenReturn(true);
        //when
        boolean result = patientService.existPatientById(1L);
        //then
        assertTrue(result);
    }

    private PatientDto createPatientDto(long id, String firstName, LocalDate birthDate, String ssn) {
        return PatientDto.builder()
                .id(id)
                .firstName(firstName)
                .birthDate(birthDate)
                .ssn(ssn)
                .build();
    }

    private Patient createPatient(long id, String firstName, LocalDate birthDate, String ssn) {
        return Patient.builder()
                .id(id)
                .firstName(firstName)
                .birthDate(birthDate)
                .ssn(ssn)
                .build();
    }
}
