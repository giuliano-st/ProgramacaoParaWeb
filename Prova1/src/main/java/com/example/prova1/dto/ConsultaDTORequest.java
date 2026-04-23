package com.example.prova1.dto;

import com.example.prova1.model.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaDTORequest(LocalDateTime appointmentDate, double price, String notes, StatusConsulta statusConsulta, long patientId, long doctorId) {
}
