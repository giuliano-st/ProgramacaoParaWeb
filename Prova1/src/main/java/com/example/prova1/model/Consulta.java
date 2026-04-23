package com.example.prova1.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime appointmentDate;
    private double price;
    private String notes;
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Paciente patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Medico medico;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public Paciente getPatient() {
        return patient;
    }

    public void setPatient(Paciente patient) {
        this.patient = patient;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Consulta() {
    }

    public Consulta(LocalDateTime appointmentDate, double price, String notes, StatusConsulta status, Paciente patient, Medico medico) {
        this.appointmentDate = appointmentDate;
        this.price = price;
        this.notes = notes;
        this.status = status;
        this.patient = patient;
        this.medico = medico;
    }

    public Consulta(long id, LocalDateTime appointmentDate, double price, String notes, StatusConsulta status, Paciente patient, Medico medico) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.price = price;
        this.notes = notes;
        this.status = status;
        this.patient = patient;
        this.medico = medico;
    }
}
