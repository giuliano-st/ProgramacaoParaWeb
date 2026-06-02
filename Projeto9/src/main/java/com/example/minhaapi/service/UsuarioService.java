package com.example.minhaapi.service;

import com.example.minhaapi.dto.TelefoneDTORequest;
import com.example.minhaapi.dto.UsuarioDTORequest;
import com.example.minhaapi.dto.UsuarioDTOResponse;
import com.example.minhaapi.mapper.TelefoneMapper;
import com.example.minhaapi.model.Telefone;
import com.example.minhaapi.model.Usuario;
import com.example.minhaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //public @ResponseStatus String save(UsuarioDTORequest usuarioDTO)
    public UsuarioDTOResponse save(UsuarioDTORequest usuarioDTO) {
        if(usuarioDTO.senha().length() <  8) {
            throw new RuntimeException("Senha deve ser maior que 8 caracteres");
        }
        if(!usuarioDTO.senha().equals(usuarioDTO.confirmaSenha())) {
            throw new RuntimeException("Senhas não coincidem");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.email());
        usuario.setNome(usuarioDTO.nome());
        usuario.setSenha(usuarioDTO.senha());
        usuarioDTO.telefones().forEach(dto -> {
            Telefone tel = TelefoneMapper.toEntity(dto);
            usuario.addTelefone(tel);
        });

        usuarioRepository.save(usuario);
        return new UsuarioDTOResponse(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefones() != null
                        ? usuario.getTelefones().stream().map(TelefoneMapper::toDTO).toList()
                        : List.of()
        );
    }

    public List<UsuarioDTOResponse> listar() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTOResponse(
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getTelefones()
                                .stream()
                                .map(TelefoneMapper::toDTO)
                                .toList()
                ))
                .toList();
    }

    public UsuarioDTOResponse atualizar(Long id, UsuarioDTORequest usuarioDTO) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuarioDTO.senha().length() < 8) {
            throw new RuntimeException("Senha deve ser maior que 8 caracteres");
        }

        if (!usuarioDTO.senha().equals(usuarioDTO.confirmaSenha())) {
            throw new RuntimeException("Senhas não coincidem");
        }

        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());

        if (usuarioDTO.telefones() != null) {

            List<Telefone> telefonesExistentes = usuario.getTelefones();

            for (TelefoneDTORequest dto : usuarioDTO.telefones()) {

                Telefone telefone = new Telefone();
                TelefoneMapper.updateEntity(telefone, dto);
                telefone.setUsuario(usuario);
                telefonesExistentes.add(telefone);
            }
            usuario.setTelefones(telefonesExistentes);
        }

        usuarioRepository.save(usuario);

        return new UsuarioDTOResponse(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefones()
                        .stream()
                        .map(TelefoneMapper::toDTO)
                        .toList()
        );
    }

    public void deletar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
}
