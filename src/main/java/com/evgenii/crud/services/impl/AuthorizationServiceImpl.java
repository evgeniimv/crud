package com.evgenii.crud.services.impl;

import com.evgenii.crud.services.AuthorizationService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Override
    public String checkAuthorization(String user) {
        return "Unauthorized User";
    }
}
