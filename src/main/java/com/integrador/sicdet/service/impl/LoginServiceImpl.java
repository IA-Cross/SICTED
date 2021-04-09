package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.config.UserToken;
import com.integrador.sicdet.entity.*;
import com.integrador.sicdet.others.EmailValidation;
import com.integrador.sicdet.repository.CroleRepository;
import com.integrador.sicdet.repository.TpersonRepository;
import com.integrador.sicdet.repository.TuserRepository;
import com.integrador.sicdet.repository.TuserroleRepository;
import com.integrador.sicdet.service.LoginService;
import com.utilssected.sected.exception.AppException;
import com.utilssected.sected.exception.AppException400BadRequest;
import com.utilssected.sected.exception.AppException401Unauthorized;
import com.utilssected.sected.utils.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private TuserRepository userRepo;
    @Autowired
    private TuserroleRepository userRole;
    @Autowired
    private CroleRepository role;
    @Autowired
    private TpersonRepository personRepo;

    @Override
    public UserToken createToken(Map<String, String> data, Map<String, String> headers) throws AppException {
        UserToken userToken = new UserToken();
        CreateTokenResponseBody tokenResponseBody = new CreateTokenResponseBody();
        Tuser user = new Tuser();
        Date localDT = java.sql.Timestamp.valueOf(LocalDateTime.now());
        try {
        }catch(Exception e) {
            Utils.raise(e, "Error al verificar usuario");
        }
        return userToken;


    }
}
