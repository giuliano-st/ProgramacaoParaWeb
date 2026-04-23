package com.example.prova1.model;

import jakarta.persistence.*;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String speciality;
    private String crm;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Medico() {
    }

    public Medico(String name, String speciality, String crm) {
        this.name = name;
        this.speciality = speciality;
        this.crm = crm;
    }

    public Medico(long id, String name, String speciality, String crm) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.crm = crm;
    }
}
