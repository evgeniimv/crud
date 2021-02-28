package com.evgenii.crud.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventCode {
    // first - timestamp, second - session, third - user
    CRUD_USERS_GET_ALL("Request for a list of registered users",
            "'%s' within a session '%s' user '%s' made a request to get a list of all registered users"),
    CRUD_USERS_GET_BY_ID("Request to get a user by the passed ID",
            "'%s' within a session '%s' user '%s' made a request to get a user by the passed ID: '%s'"),
    CRUD_USERS_CREATE("Request to create a new user",
            "'%s' within a session '%s' user '%s' made a request to create new user - Name: '%s', Email: '%s'");

    String title;
    String messageTemplate;
}
