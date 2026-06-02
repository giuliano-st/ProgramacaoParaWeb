package com.example.minhaapi.mapper;

import com.example.minhaapi.dto.TelefoneDTORequest;
import com.example.minhaapi.dto.TelefoneDTOResponse;
import com.example.minhaapi.model.Telefone;

public class TelefoneMapper {

    public static Telefone toEntity(TelefoneDTORequest dto) {
        Telefone telefone = new Telefone();
        telefone.setNumero(dto.numero());
        telefone.setTipo(dto.tipo());
        return telefone;
    }
    /*Conversão de DTO para entidade no banco de dados*/

    public static TelefoneDTOResponse toDTO(Telefone telefone) {
        return new TelefoneDTOResponse(
                telefone.getNumero(),
                telefone.getTipo()
        );
    }
    /*Conversão de entidade do banco para DTO (pra recuperar os dados)*/

    public static void updateEntity(Telefone telefone, TelefoneDTORequest dto) {
        if (dto.numero() != null) {
            telefone.setNumero(dto.numero());
        }

        if (dto.tipo() != null) {
            telefone.setTipo(dto.tipo());
        }
    }
    /*Atualização parcial dos dados da entidade*/
}
