package com.integrador.sicdet.config;

import com.integrador.sicdet.entity.CreateTokenResponseBody;

public class UserToken {
    CreateTokenResponseBody user;

    public CreateTokenResponseBody getUser() {
        return user;
    }

    public void setUser(CreateTokenResponseBody user) {
        this.user = user;
    }
}
