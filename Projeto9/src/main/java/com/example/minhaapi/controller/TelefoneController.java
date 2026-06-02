package com.example.minhaapi.controller;

import com.example.minhaapi.dto.TelefoneDTORequest;
import com.example.minhaapi.dto.TelefoneDTOResponse;
import com.example.minhaapi.mapper.TelefoneMapper;
import com.example.minhaapi.model.Telefone;
import com.example.minhaapi.repository.TelefoneRepository;
import com.example.minhaapi.service.TelefoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    private final TelefoneService telefoneService;
    private final TelefoneRepository telefoneRepository;

    public TelefoneController(TelefoneService telefoneService, TelefoneRepository telefoneRepository) {
        this.telefoneService = telefoneService;
        this.telefoneRepository = telefoneRepository;
    }

    //public @ResponseStatus String salvar(@RequestBody TelefoneDTORequest telefoneDTO) {
    @PostMapping
    public TelefoneDTOResponse salvar(@RequestBody TelefoneDTORequest telefoneDTO) {
        return telefoneService.save(telefoneDTO);
    }

    @GetMapping
    public List<TelefoneDTOResponse> listar(){
        return telefoneService.listar();
    }

    @GetMapping("/{id}")
    public TelefoneDTOResponse atualizar(Long id, TelefoneDTORequest dto) {
        Telefone telefone = telefoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        TelefoneMapper.updateEntity(telefone, dto);

        telefoneRepository.save(telefone);

        return TelefoneMapper.toDTO(telefone);
    }
}
