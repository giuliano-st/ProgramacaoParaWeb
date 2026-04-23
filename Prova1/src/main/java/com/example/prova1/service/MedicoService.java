package com.example.prova1.service;

import com.example.prova1.dto.MedicoDTORequest;
import com.example.prova1.dto.MedicoDTOResponse;
import com.example.prova1.model.Medico;
import com.example.prova1.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoDTOResponse saveMedico(MedicoDTORequest medicoDTO) {
        Medico medico = new Medico();
        medico.setName(medicoDTO.name());
        medico.setSpeciality(medicoDTO.speciality());
        medico.setCrm(medicoDTO.crm());
        medicoRepository.save(medico);
        return new MedicoDTOResponse(
                medico.getId(),
                medico.getName(),
                medico.getSpeciality(),
                medico.getCrm()
        );
    }

    public List<MedicoDTOResponse> listMedico() {
        List<Medico> medicos = (List<Medico>) medicoRepository.findAll();
        return medicos.stream().map(medico -> new MedicoDTOResponse(medico.getId(), medico.getName(), medico.getSpeciality(), medico.getCrm())).toList();
    }

    public MedicoDTOResponse updateMedico(Long id, MedicoDTORequest medicoDTO) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        medico.setName(medicoDTO.name());
        medico.setSpeciality(medicoDTO.speciality());
        medico.setCrm(medicoDTO.crm());
        medicoRepository.save(medico);
        return new MedicoDTOResponse(medico.getId(), medico.getName(), medico.getSpeciality(), medico.getCrm());
    }

    public void deleteMedico(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        medicoRepository.delete(medico);
    }
}
