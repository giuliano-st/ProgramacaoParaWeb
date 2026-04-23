package com.example.prova1.controller;

import com.example.prova1.dto.MedicoDTORequest;
import com.example.prova1.dto.MedicoDTOResponse;
import com.example.prova1.service.MedicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public @ResponseStatus MedicoDTOResponse salvar(@RequestBody MedicoDTORequest medicoDTO) {
        return medicoService.saveMedico(medicoDTO);
    }

    @GetMapping
    public List<MedicoDTOResponse> listar() {return medicoService.listMedico();}

    @PutMapping("/{id}")
    public MedicoDTOResponse atualizar(@PathVariable Long id, @RequestBody MedicoDTORequest medicoDTO) {
        return medicoService.updateMedico(id, medicoDTO);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        medicoService.deleteMedico(id);
    }
}
