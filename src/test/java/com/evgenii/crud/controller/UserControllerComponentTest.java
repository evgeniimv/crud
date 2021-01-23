package com.evgenii.crud.controller;


import com.evgenii.crud.BaseComponentTest;
import com.evgenii.crud.config.ComponentTest;
import com.evgenii.crud.dto.UserDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentTest
public class UserControllerComponentTest extends BaseComponentTest {

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

        List<UserDto> users = mapToListOfObjects(response, UserDto.class);

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
        final UserDto createdUser = mapToObject(response, UserDto.class);

        assertThat(createdUser)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(userDto);
    }
}
