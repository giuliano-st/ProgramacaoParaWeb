package com.example.projeto7.controller;

import com.example.projeto7.model.Telefone;
import com.example.projeto7.model.Usuario;
import com.example.projeto7.repository.UsuarioRepository;
import com.example.projeto7.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping() /*
    public Usuario salvar(@RequestBody UsuarioDTORequest usuarioDTO) {
        return usuarioService.save(usuarioDTO);
    }*/
    public @ResponseStatus String salvar(@RequestBody Usuario usuario) {
        usuario.getTelefones().forEach(telefone -> telefone.setUsuario(usuario));
        usuarioRepository.save(usuario);
        return "Salvo com sucesso!";
    }

    @GetMapping
    public List<Usuario> listar() {
        //return usuarioService.listar();
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public @ResponseStatus String deletar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "Deletado com sucesso!";
    }

    @PutMapping()
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());
        if (usuarioOptional.isPresent()) {
            Usuario usuarioAtualizado = usuarioOptional.get();
            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setEmail(usuario.getEmail());
            usuarioAtualizado.setSenha(usuario.getSenha());
            usuarioAtualizado.setAtivo(true); //Reseta a lista antes de salvar
            usuarioAtualizado.getTelefones().clear();
            for (Telefone telefone : usuario.getTelefones()) {
                usuarioAtualizado.getTelefones().add(telefone);
            }
            usuarioRepository.save(usuarioAtualizado);
            return ResponseEntity.ok(usuarioAtualizado);
        }
        return ResponseEntity.notFound().build();
    }
    /*

    @DeleteMapping("/{id}")
    public void deleta(@PathVariable Long id) {
        usuarioRepository.findById(id).ifPresent(usuario -> {usuario.setAtivo(false);});
    }

    @PutMapping
    public Usuario atualizar(@RequestBody UsuarioDTORequest usuarioDTO) {
        return usuarioService.update(usuarioDTO);
    }
    */
    /*
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleta(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @PutMapping
    public Usuario atualizar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
     */
}
