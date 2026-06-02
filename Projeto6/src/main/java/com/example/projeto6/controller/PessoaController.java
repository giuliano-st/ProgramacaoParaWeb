package com.example.projeto6.controller;

import com.example.projeto6.model.Pessoa;
import com.example.projeto6.repository.PessoaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping()
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @GetMapping
    public List<Pessoa> listar() {
        return (List<Pessoa>) pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPorId(@PathVariable Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    @GetMapping("/cpf/{cpf}")
    public Pessoa buscarPorCpf(@PathVariable String cpf) {
        return pessoaRepository.findByCpf(cpf).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleta(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
    }

    @PutMapping
    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
