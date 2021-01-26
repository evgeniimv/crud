package com.evgenii.crud.config;

import com.evgenii.crud.services.AuthorizationService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerTestBeans {
    @MockBean
    protected AuthorizationService authorizationService;
}
