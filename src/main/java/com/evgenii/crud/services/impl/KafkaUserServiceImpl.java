package com.evgenii.crud.services.impl;

import com.evgenii.crud.dto.UserDto;
import com.evgenii.crud.services.KafkaUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserServiceImpl implements KafkaUserService {
    private final KafkaTemplate<Long, UserDto> kafkaUserTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(UserDto dto) {
        log.info("=> produced {}", writeValueAsString(dto));
        kafkaUserTemplate.send("user", dto);
    }

    @Override
    @KafkaListener(id = "User", topics = {"user"}, containerFactory = "singleFactory")
    public void consume(UserDto dto) {
        log.info("=> consumed {}", writeValueAsString(dto));
    }

    private String writeValueAsString(UserDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
