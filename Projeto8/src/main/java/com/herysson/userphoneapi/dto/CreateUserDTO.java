package com.herysson.userphoneapi.dto;

import java.util.List;

public record CreateUserDTO(String name, List<CreatePhoneDTO> phones) {
}
