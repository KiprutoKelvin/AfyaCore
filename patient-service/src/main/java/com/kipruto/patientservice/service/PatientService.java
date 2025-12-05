package com.kipruto.patientservice.service;

import com.kipruto.patientservice.dto.PatientRequestDTO;
import com.kipruto.patientservice.dto.PatientResponseDTO;
import com.kipruto.patientservice.mapper.PatientMapper;
import com.kipruto.patientservice.model.Patient;
import com.kipruto.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream() //inline variable
                .map(PatientMapper::toDTO).toList(); // Used lambda method reference for mapping instead of .map(patient -> PatientMapper.toDTO(patient)).toList();
    }

    public  PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO));

        return  PatientMapper.toDTO(newPatient);
    }
}
