package com.herysson.userphoneapi.mapper;

import com.herysson.userphoneapi.dto.CreatePhoneDTO;
import com.herysson.userphoneapi.dto.PhoneDTO;
import com.herysson.userphoneapi.dto.UpdatePhoneDTO;
import com.herysson.userphoneapi.model.Phone;

public class PhoneMapper {

    public static PhoneDTO toDTO(Phone phone) {
        return new PhoneDTO(
                phone.getId(),
                phone.getNumber(),
                phone.getType()
        );
    }

    public static Phone toEntity(CreatePhoneDTO dto) {
        Phone phone = new Phone();
        phone.setNumber(dto.number());
        phone.setType(dto.type());
        return phone;
    }

    public static void updateEntity(Phone phone, UpdatePhoneDTO dto) {

        if (dto.number() != null) {
            phone.setNumber(dto.number());
        }

        if (dto.type() != null) {
            phone.setType(dto.type());
        }
    }
}
