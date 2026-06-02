package com.example.minhaapi.service;

import com.example.minhaapi.dto.TelefoneDTORequest;
import com.example.minhaapi.dto.TelefoneDTOResponse;
import com.example.minhaapi.mapper.TelefoneMapper;
import com.example.minhaapi.model.Telefone;
import com.example.minhaapi.repository.TelefoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;

    public TelefoneService(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }
    //public @ResponseStatus String save(TelefoneDTORequest telefoneDTO)
    public TelefoneDTOResponse save(TelefoneDTORequest telefoneDTO) {
        Telefone telefone = TelefoneMapper.toEntity(telefoneDTO);
        telefoneRepository.save(telefone);
        return TelefoneMapper.toDTO(telefone);
    }
    /*Conversão do telefone mapeado no DTO em entidade do banco*/

    public List<TelefoneDTOResponse> listar() {
        List<Telefone> telefones = (List<Telefone>) telefoneRepository.findAll();
        List<TelefoneDTOResponse> lista = new ArrayList<>();
        for (Telefone telefone : telefones) {
            lista.add(TelefoneMapper.toDTO(telefone));
        }

        return lista;
    }
    /*Conversão da entidade Usuário para o DTOResponse*/
}
