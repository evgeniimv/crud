package com.evgenii.crud;


import com.evgenii.crud.config.ComponentTest;
import com.evgenii.crud.dto.UserDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentTest
public class UserControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Sql({
            "/db-test-case/default-user.sql"
    })
    @Test
    public void checkGetAllUsers() throws Exception {
        final String response = mockMvc.perform(
                get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<UserDto> users = objectMapper.readValue(response, new TypeReference<List<UserDto>>() {
        });

        assertThat(users)
                .hasSize(1).element(0)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(getExpectedUser());
    }

    private UserDto getExpectedUser() {
        return UserDto.builder()
                .name("John Snow")
                .email("email@gmail.com")
                .build();
    }

    @Test
    @SneakyThrows
    public void checkCreateUser() {
        UserDto userDto = getExpectedUser();

        final String response = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final UserDto createdUser = objectMapper.readValue(response, UserDto.class);

        assertThat(createdUser)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(userDto);
    }
}
