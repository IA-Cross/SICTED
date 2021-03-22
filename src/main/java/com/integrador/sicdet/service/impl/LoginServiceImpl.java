package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.config.UserToken;
import com.integrador.sicdet.entity.CreateTokenResponseBody;
import com.integrador.sicdet.entity.Tuser;
import com.integrador.sicdet.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

    @Override
    public UserToken createToken(Map<String, String> data, Map<String, String> headers) {
        UserToken userToken = new UserToken();
        CreateTokenResponseBody tokenResponseBody = new CreateTokenResponseBody();
        Tuser user = new Tuser();
        try{
            if(data.isEmpty() || (!data.containsKey("identifier") || !data.containsKey("password") || !data.containsKey("token-type"))
                    || (data.get("identifier") == null && data.get("password") == null || data.get("token-type") == null)
                    || (!data.get("token-type").equals("1") && !data.get("token-type").equals("2"))) {
                LOG.info("!400	Invalid input");

            }
        }catch(Exception e){

        }
        return null;
    }
}
