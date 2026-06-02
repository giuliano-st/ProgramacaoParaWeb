package com.herysson.userphoneapi.service;

import com.herysson.userphoneapi.dto.CreateUserDTO;
import com.herysson.userphoneapi.mapper.PhoneMapper;
import com.herysson.userphoneapi.model.Phone;
import com.herysson.userphoneapi.model.User;
import com.herysson.userphoneapi.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDTO dto) {

        User user = new User();
        user.setName(dto.name());

        List<Phone> phones = dto.phones()
                .stream()
                .map(PhoneMapper::toEntity)
                .toList();

        phones.forEach(phone -> phone.setUser(user));

        user.setPhones(phones);

        return userRepository.save(user);
    }
}
