package com.example.minhaapi.controller;

import com.example.minhaapi.dto.UsuarioDTORequest;
import com.example.minhaapi.dto.UsuarioDTOResponse;
import com.example.minhaapi.model.Usuario;
import com.example.minhaapi.repository.UsuarioRepository;
import com.example.minhaapi.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public @ResponseStatus UsuarioDTOResponse salvar(@RequestBody UsuarioDTORequest usuarioDTO){
        return usuarioService.save(usuarioDTO);
    }

    @PostMapping("/multiplos")
    public @ResponseStatus String salvarMultiplos(@RequestBody List<Usuario> usuarios){
        usuarioRepository.saveAll(usuarios);
        return "Salvo com sucesso!";
    }

    @GetMapping
    public List<UsuarioDTOResponse> listar(){
        return usuarioService.listar();
    }

    @PutMapping("/{id}")
    public UsuarioDTOResponse atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioDTORequest usuarioDTO
    ) {
        return usuarioService.atualizar(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}
