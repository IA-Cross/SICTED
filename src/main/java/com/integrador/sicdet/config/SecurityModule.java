package com.integrador.sicdet.config;

import com.integrador.sicdet.entity.Turl;
import com.integrador.sicdet.others.EncryptionClasses;
import com.integrador.sicdet.repository.TurlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityModule implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityModule.class);

    @Autowired
    private CorsConfiguration corsConfiguration;
    @Autowired
    private TurlRepository urlRepo;

    @Bean
    CorsConfiguration corsFilter() {
        return corsConfiguration;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        //get user-agent
        String userAgent = request.getHeader("User-Agent");
        //get token
        String token = request.getHeader("Authorization");
        //get ip
        String ipAddress = request.getRemoteAddr();

        try{
            Turl urlBd = urlRepo.findByUrl(url);
            if(urlBd == null){
                LOG.error(">>> The Url not exist <<<");
                response.setStatus(400);
                return false;
            }

            if (urlBd.getIsPublic() == 1) {
                LOG.info(">>> Access Granted, The Url is public <<<");
                return true;
            }

            if (!token.isEmpty()) {

            }else{
                LOG.info(">>>  Token Not Found <<<");
                response.setStatus(400);
                return false;
            }
        }catch (Exception e){
            LOG.error(">>> Corrupt Credentials <<< error:{}",e);
            response.setStatus(400);
            return false;
        }
        return true;
    }

}

