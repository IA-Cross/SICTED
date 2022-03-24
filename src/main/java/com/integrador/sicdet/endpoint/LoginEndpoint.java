package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.config.UserToken;
import com.integrador.sicdet.service.LoginService;
import com.utilssected.sected.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import com.utilssected.sected.utils.ResponseBody;

@RestController
public class LoginEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(LoginEndpoint.class);

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<ResponseBody<UserToken>> createToken(@RequestParam Map<String,String>params, @RequestHeader Map<String, String> headers){

        ResponseEntity<ResponseBody<UserToken>> res = null;
        UserToken userToken = new UserToken();
        try {
            userToken = loginService.createToken(params, headers);
            System.out.println("Login contoller");
            res=Utils.response200OK(userToken);
        }catch(Exception e) {
            LOG.info("ERROR");
            res = Utils.handle(e, "Error");
        }

        return res;
    }


    @PostMapping("/recover")
    public ResponseEntity<ResponseBody<Void>> insert(@RequestParam Map<String,String>params){
        ResponseEntity<ResponseBody<Void>>res=null;
        LOG.info("<<<Recover Password by Email>>>");

        try {
            String mail = params.get("email");
            loginService.recoverPassword(mail);
            res = new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            res = new ResponseEntity<>(HttpStatus.METHOD_FAILURE);
        }


        return res;
    }
}
