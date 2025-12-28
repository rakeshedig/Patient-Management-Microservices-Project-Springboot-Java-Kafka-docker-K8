package com.pm.patientservice.mapper;

import java.time.LocalDate;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        // registeredDate is handled in service layer (auto-set if null during creation)
        // This is a safety check - service should always set it before calling mapper
        if (patientRequestDTO.getRegisteredDate() != null && !patientRequestDTO.getRegisteredDate().isEmpty()) {
            patient.setRegistrationDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        } else {
            // Fallback: set to current date if somehow still null (shouldn't happen)
            patient.setRegistrationDate(LocalDate.now());
        }
        return patient;
    }

}
