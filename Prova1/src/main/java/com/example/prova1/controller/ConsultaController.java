package com.example.prova1.controller;

import com.example.prova1.dto.ConsultaDTORequest;
import com.example.prova1.dto.ConsultaDTOResponse;
import com.example.prova1.service.ConsultaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public @ResponseStatus ConsultaDTOResponse salvar(@RequestBody ConsultaDTORequest consultaDTO) {
        return consultaService.saveConsulta(consultaDTO);
    }

    @GetMapping
    public List<ConsultaDTOResponse> listar() {return consultaService.listConsulta();}
}
