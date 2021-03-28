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
            //Verificar parametros input
            if(data.isEmpty() || (!data.containsKey("identifier") || !data.containsKey("password") || !data.containsKey("token-type"))
                    || (data.get("identifier") == null && data.get("password") == null || data.get("token-type") == null)
                    || (!data.get("token-type").equals("1") && !data.get("token-type").equals("2"))) {
                LOG.info("!400	Invalid input");
                throw new AppException400BadRequest("Invalid input");
            }
            //Verificar si el identifier es correo o identificador
            if(EmailValidation.isValidEmail(data.get("identifier"))){
                if(userRepo.existsByEmail(data.get("identifier"))) {
                    user = userRepo.findByEmail(data.get("identifier"));
                }else {
                    LOG.info("!401	Ivalid email");
                    throw new AppException401Unauthorized();//Correo invalido
                }
            }else {
                if(userRepo.existsByOtherIdentifier(data.get("identifier"))) {
                    user = userRepo.findByOtherIdentifier(data.get("identifier"));
                }else {
                    LOG.info("!401	Invalid nickname");
                    throw new AppException401Unauthorized();//Nombre de usuario invalido
                }
            }
            //Comprobación de contraseña
            if(!user.getPassword().equals(data.get("password"))) {
                    LOG.info("!401 Invalid user or ip");
                    throw new AppException401Unauthorized();//Se aumenta el numero de intentos de login

            }

            List<Tuserrole> userRoles = null;
            Map<String, String> rolesHash = new HashMap<String,String>();
            List<String> roles = new ArrayList<>();
            List<Crole> rols = null;

            //Si existe alguna relacion de rol por idUser
            //if(userRole.existsByIdUser(user.getId())) {
            boolean student=false;
            String rolesDecription="";
            List<String> rolesDescriptionList = new ArrayList();
            if(userRole.findByIdUser(user.getId())!=null) {
                userRoles = userRole.findAllByIdUser(user.getId());
                Iterator<Tuserrole> it= userRoles.iterator();
                while(it.hasNext()) {
                    int li = it.next().getIdrol();
                    Crole ole = role.findById(li);
                    rolesDecription=rolesDecription.concat(ole.getDescription()+",");
                    roles.add(ole.getDescription());
                    //Si es un estudiante se activa esta bandera
                    if (ole.getId()==1){
                        student=true;
                    }
                }//Guardamos los roles para despuesa
                rolesHash.put("descriptions",rolesDecription);
            }else {
                LOG.info("!403	User does not have any role");
                //throw new AppException403Forbidden(); //User does not have any role
            }

            Map<String, String> person = new HashMap<String,String>();


            Tperson personVO = new Tperson();
            /*if(user.getIdperson() != null) {
                Tperson di = user.getIdperson();
                personVO = personRepo.findByIdActive(di.getId());
                person.put("name", personVO.getName());
                person.put("lastname", personVO.getFirstLastName()+" "+personVO.getSecondLastName());
                person.put("id",personVO.getId().toString());
                if (student){
                    person.put("idStudent",user.getIdStudent().toString());
                }else{
                    person.put("idEmployee",user.getIdEmployee().toString());
                }

                //.... agregar los demas roles, student, employee, etc.
            }*/


            Map<String, String> session = new HashMap<String,String>();
            session.put("user-agent",headers.get("user-agent"));
            session.put("token-type", data.get("token-type"));

            Map<String, String> userData = new HashMap<String,String>();
            userData.put("id", user.getId().toString());
            userData.put("email", user.getEmail());
            
            //userData.put("ipAuthorized", user)


            ////----->>> CREACION DEL TOKEN <<<------
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            //Buscar una forma de guardar la key o palabra secreta
            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

            //Creating the Header of
            HashMap<String, Object> tokenHeader = new HashMap<String,Object>();
            tokenHeader.put("alg", signatureAlgorithm.toString()); //HS256
            tokenHeader.put("typ","JWT");

            //Date tokenExpirationDate = NewBlockedDate.BlockedDate(30);

            //Generate tokenBody + adding header + adding payload
            String tokenBody = Jwts.builder()
                    .setHeader(tokenHeader)
                    .claim("session",session)
                    .claim("user", userData)
                    .claim("roles", rolesHash)
                    // Fecha de creacion
                    .setIssuedAt(localDT)
                    // Fecha de expiracon
                    //.setExpiration(tokenExpirationDate)
                    .signWith(key)
                    .compact();

            String token = Jwts.builder()
                    .setHeader(tokenHeader)
                    .setSubject(tokenBody)
                    .signWith(key)
                    .compact();
            String hashC = String.valueOf(token.hashCode());
            Map<String, String> dataTokeS = new HashMap<String,String>();
            //dataTokeS.put("otherIdentifier", user.getOtherIdentifier());
            dataTokeS.put("hashCode", hashC);

            String tokenSecundaryBody = Jwts.builder()
                    .setHeader(tokenHeader)
                    .claim("user",dataTokeS)
                    .signWith(key)
                    .compact();

            String tokenSecundary = Jwts.builder()
                    //.signWith(SignatureAlgorithm.HS256, "P4tit064")
                    .setHeader(tokenHeader)
                    .setSubject(tokenSecundaryBody)
                    .signWith(key)
                    .compact();


            //tokenResponseBody.setTokenSecundario(tokenSecundary);
            tokenResponseBody.setClaveHash(hashC);
            tokenResponseBody.setEmail(user.getEmail());
            //tokenResponseBody.setOtherIdentifier(user.getOtherIdentifier());
            tokenResponseBody.setRoles(roles);
            tokenResponseBody.setPerson(person);


            /*user.setToken(token);
            user.setTokenCreatedAt(localDT);
            user.setTokenExpirationDate(tokenExpirationDate);
            user.setTokenLastTen(hashC);
            user.setFailedLogin(0);*/
            userRepo.save(user);
            userToken.setUser(tokenResponseBody);
        }catch(Exception e) {
            Utils.raise(e, "Error al verificar usuario");
        }
        return userToken;


    }
}
