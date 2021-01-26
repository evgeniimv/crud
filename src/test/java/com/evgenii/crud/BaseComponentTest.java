package com.evgenii.crud;

import com.evgenii.crud.config.ComponentTest;
import com.evgenii.crud.services.AuthorizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ComponentTest
public class BaseComponentTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private AuthorizationService authorizationService;

    @BeforeEach
    public void init() {
        when(authorizationService.checkAuthorization(any())).thenReturn(null);
    }


    protected <T> T mapToObject(String input, Class<T> clazz) throws IOException {
        return objectMapper.readValue(input, clazz);
    }

    protected <T> List<T> mapToListOfObjects(String input, Class<T> objectType) throws IOException {
        CollectionType type = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, objectType);
        return objectMapper.readValue(input, type);
    }
}
