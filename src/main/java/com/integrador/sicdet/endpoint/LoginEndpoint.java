package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.config.UserToken;
import com.integrador.sicdet.service.LoginService;
import com.utilssected.sected.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    /*@GetMapping("/login")
    public ResponseEntity<ResponseBody<UserToken>> createToken(@RequestParam Map<String,String> params, @RequestHeader Map<String, String> headers){
        System.out.println("Login contoller");
        ResponseEntity<ResponseBody<UserToken>> res = null;

        UserToken userToken = new UserToken();
        try {
            userToken = loginService.createToken(params, headers);
            //res = new ResponseEntity<>(userToken, HttpStatus.OK);
            res= Utils.response200OK(userToken);
        }catch(Exception e) {
            //res = new ResponseEntity<>(userToken, HttpStatus.BAD_REQUEST);
            res= Utils.handle(e, "Error");
        }
        //token = loginService.createToken(params, headers);
        return res;
    }*/
}
