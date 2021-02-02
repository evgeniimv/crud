package com.evgenii.crud.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventCode {
    // first - timestamp, second - session, third - user
    CRUD_USERS_GET_ALL("Запрос на получение списка зарегистрированных пользователей",
            "'%s' в рамках сессии '%s' пользователь '%s' осуществил "
                    + "запрос на получение списка всех зарегистрированных пользователей"),
    CRUD_USERS_GET_BY_ID("Запрос на получение пользователя по переданному ID",
                               "'%s' в рамках сессии '%s' пользователь '%s' осуществил "
                               + "запрос на получение пользователя по переданному ID: '%s'"),
    CRUD_USERS_CREATE("Запрос на создание пользователя",
                                 "'%s' в рамках сессии '%s' пользователь '%s' осуществил "
                                 + "запрос на создание пользователя - Имя: '%s', Email: '%s'");

    String title;
    String messageTemplate;
}
