package com.herysson.userphoneapi.service;

import com.herysson.userphoneapi.dto.CreatePhoneDTO;
import com.herysson.userphoneapi.mapper.PhoneMapper;
import com.herysson.userphoneapi.model.Phone;
import com.herysson.userphoneapi.repository.PhoneRepository;

public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Phone save(CreatePhoneDTO dto) {
        Phone phone = PhoneMapper.toEntity(dto);
        return phoneRepository.save(phone);
    }
}
