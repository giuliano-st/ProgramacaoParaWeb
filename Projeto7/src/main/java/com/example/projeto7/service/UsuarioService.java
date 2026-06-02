package com.example.projeto7.service;

import com.example.projeto7.dto.UsuarioDTORequest;
import com.example.projeto7.dto.UsuarioDTOResponse;
import com.example.projeto7.model.Usuario;
import com.example.projeto7.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario save(UsuarioDTORequest usuarioDTO) {
        if (usuarioDTO.senha().length() < 8) {
            throw new IllegalArgumentException("Senha deve ser maior que 8 caracteres");
        }
        if (!usuarioDTO.senha().equals(usuarioDTO.confirmaSenha())) {
            throw new IllegalArgumentException("Senhas não coincidem");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.email());
        usuario.setNome(usuarioDTO.nome());
        usuario.setSenha(usuarioDTO.senha());

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioDTOResponse> listar() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        List<UsuarioDTOResponse> usuarioDTOResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDTOResponses.add(new UsuarioDTOResponse(usuario.getNome(), usuario.getSenha()));
        }
        return usuarioDTOResponses;
    }

    public Usuario update(UsuarioDTORequest usuarioDTO) {
        if (usuarioDTO.senha().length() < 8) {
            throw new IllegalArgumentException("Senha deve ser maior que 8 caracteres");
        }
        if (!usuarioDTO.senha().equals(usuarioDTO.confirmaSenha())) {
            throw new IllegalArgumentException("Senhas não coincidem");
        }

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.id());
        usuario.setEmail(usuarioDTO.email());
        usuario.setNome(usuarioDTO.nome());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setAtivo(true);

        return usuarioRepository.save(usuario);
    }
}
