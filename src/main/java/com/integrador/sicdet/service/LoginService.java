package com.integrador.sicdet.service;

import com.integrador.sicdet.config.UserToken;
import com.utilssected.sected.exception.AppException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface LoginService {

    UserToken createToken(@RequestParam Map<String,String>params, @RequestHeader Map<String, String> headers) throws AppException;

}
