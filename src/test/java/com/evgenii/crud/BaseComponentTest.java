package com.evgenii.crud;

import com.evgenii.crud.config.ComponentTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

@ComponentTest
public class BaseComponentTest {
    @Autowired
    public MockMvc mockMvc;
    @Autowired
    public ObjectMapper objectMapper;

    public <T> T mapToObject(String input, Class<T> clazz) throws IOException {
        return objectMapper.readValue(input, clazz);
    }

    public <T, R extends List<T>> List<T> mapToListOfObjects(String input, Class<T> objectType
    ) throws IOException {
        CollectionType type = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, objectType);
        return objectMapper.readValue(input, type);
    }
}
