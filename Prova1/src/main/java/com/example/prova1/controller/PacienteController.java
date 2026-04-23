package com.example.prova1.controller;

import com.example.prova1.dto.PacienteDTORequest;
import com.example.prova1.dto.PacienteDTOResponse;
import com.example.prova1.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public @ResponseStatus PacienteDTOResponse salvar(@RequestBody PacienteDTORequest pacienteDTO) {
        return pacienteService.savePaciente(pacienteDTO);
    }

    @GetMapping
    public List<PacienteDTOResponse> listar() {return pacienteService.listPaciente();}

    @PutMapping("/{id}")
    public PacienteDTOResponse atualizar(@PathVariable Long id, @RequestBody PacienteDTORequest pacienteDTO) {
        return pacienteService.updatePaciente(id, pacienteDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
    }
}
