package com.herysson.userphoneapi.controller;

import com.herysson.userphoneapi.dto.CreatePhoneDTO;
import com.herysson.userphoneapi.dto.PhoneDTO;
import com.herysson.userphoneapi.dto.UpdatePhoneDTO;
import com.herysson.userphoneapi.mapper.PhoneMapper;
import com.herysson.userphoneapi.model.Phone;
import com.herysson.userphoneapi.repository.PhoneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    private final PhoneRepository phoneRepository;

    public PhoneController(PhoneRepository phoneRepository) {this.phoneRepository = phoneRepository; }

    @PostMapping
    public PhoneDTO createPhone(@RequestBody CreatePhoneDTO dto) {
        Phone phone = PhoneMapper.toEntity(dto);
        Phone savedPhone = phoneRepository.save(phone);
        return PhoneMapper.toDTO(savedPhone);
    }

    @GetMapping
    public List<PhoneDTO> getAllPhones() {
        return phoneRepository.findAll().stream().map(PhoneMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDTO> getPhoneById(@PathVariable Long id) {

        Optional<Phone> phone = phoneRepository.findById(id);

        return phone
                .map(PhoneMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneDTO> updatePhone(
            @PathVariable Long id,
            @RequestBody UpdatePhoneDTO dto) {

        Optional<Phone> existingPhone = phoneRepository.findById(id);

        if (existingPhone.isPresent()) {

            Phone phone = existingPhone.get();

            PhoneMapper.updateEntity(phone, dto);

            Phone savedPhone = phoneRepository.save(phone);

            return ResponseEntity.ok(PhoneMapper.toDTO(savedPhone));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
