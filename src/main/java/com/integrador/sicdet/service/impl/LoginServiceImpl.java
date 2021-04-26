package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.config.UserToken;
import com.integrador.sicdet.entity.*;
import com.integrador.sicdet.others.EmailValidation;
import com.integrador.sicdet.repository.*;
import com.integrador.sicdet.service.LoginService;
import com.utilssected.sected.exception.AppException;
import com.utilssected.sected.exception.AppException400BadRequest;
import com.utilssected.sected.exception.AppException401Unauthorized;
import com.utilssected.sected.exception.AppException403Forbidden;
import com.utilssected.sected.utils.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    @Autowired
    private TtesistaRepository tesistaRepo;

    @Override
    public UserToken createToken(Map<String, String> data, Map<String, String> headers) throws AppException {
        UserToken userToken = new UserToken();
        CreateTokenResponseBody tokenResponseBody = new CreateTokenResponseBody();
        Tuser user = new Tuser();
        Date localDT = java.sql.Timestamp.valueOf(LocalDateTime.now());
        try {
            if (data.isEmpty() || (!data.containsKey("email") || !data.containsKey("password"))
                    || (data.get("email") == null && data.get("password") == null)) {
                LOG.info("!400	Invalid input");
                throw new AppException400BadRequest("Invalid input");
            }
            if (userRepo.existsByEmail(data.get("email"))) {
                user = userRepo.findByEmail(data.get("email"));
            } else {
                LOG.info("!401	Ivalid email");
                throw new AppException401Unauthorized();//Correo invalido
            }

            if (!user.getPassword().equals(data.get("password"))) {
                LOG.info("!401 Invalid user or ip");
                throw new AppException401Unauthorized();
            }

            List<Tuserrole> userRoles = null;
            Map<String, String> rolesHash = new HashMap<String, String>();
            List<String> roles = new ArrayList<>();
            List<Crole> rols = null;
            boolean tesist = false;
            String rolesDecription = "";
            List<String> rolesDescriptionList = new ArrayList();
            if (userRole.findByIdUser(user.getId()) != null) {
                userRoles = userRole.findAllByIdUser(user.getId());
                System.out.println("Buscar ROLES "+userRoles);
                Iterator<Tuserrole> it = userRoles.iterator();
                while (it.hasNext()) {
                    int li = it.next().getIdrol().getId();
                    Crole ole = role.findById(li);
                    rolesDecription = rolesDecription.concat(ole.getDescription());
                    roles.add(ole.getDescription());
                    //Si es un estudiante se activa esta bandera
                    if (ole.getId() == 1) {
                        tesist = true;
                    }
                }//Guardamos los roles para despuesa
                rolesHash.put("descriptions", rolesDecription);
            } else {
                LOG.info("!403	User does not have any role");
                //throw new AppException403Forbidden(); //User does not have any role
            }

            Map<String, String> person = new HashMap<String, String>();

            Tperson personVO = new Tperson();
            Ttesista studentResponseVO;

            if (user.getIdperson() != null) {
                Tperson di = user.getIdperson();
                personVO = personRepo.findByIdActive(di.getId());
                person.put("name", personVO.getName());
                person.put("lastname", personVO.getFirstlastname() + " " + personVO.getSecondlastname());
                person.put("id", personVO.getId().toString());
                if (tesist) {
                    studentResponseVO = tesistaRepo.findByIdPerson(user.getIdperson().getId());
                    person.put("IdTesista", studentResponseVO.getId().toString());
                } else {
                    person.put("IdPerson", user.getIdperson().toString());
                }

                //.... agregar los demas roles, student, employee, etc.
            }

            Map<String, String> session = new HashMap<String, String>();
            session.put("user-agent", headers.get("user-agent"));
            Map<String, String> userData = new HashMap<String, String>();
            userData.put("id", user.getId().toString());
            userData.put("email", user.getEmail());

            ////----->>> CREACION DEL TOKEN <<<------
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            //Buscar una forma de guardar la key o palabra secreta
            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

            //Creating the Header of
            HashMap<String, Object> tokenHeader = new HashMap<String, Object>();
            tokenHeader.put("alg", signatureAlgorithm.toString()); //HS256
            tokenHeader.put("typ", "JWT");

            //Generate tokenBody + adding header + adding payload
            String tokenBody = Jwts.builder()
                    .setHeader(tokenHeader)
                    .claim("session", session)
                    .claim("user", userData)
                    .claim("roles", rolesHash)
                    // Fecha de creacion
                    .setIssuedAt(localDT)
                    // Fecha de expiracon
                    .signWith(key)
                    .compact();

            String token = Jwts.builder()
                    .setHeader(tokenHeader)
                    .setSubject(tokenBody)
                    .signWith(key)
                    .compact();
            String hashC = String.valueOf(token.hashCode());
            Map<String, String> dataTokeS = new HashMap<String, String>();
            dataTokeS.put("email", user.getEmail());
            dataTokeS.put("hashCode", hashC);

            tokenResponseBody.setClaveHash(hashC);
            tokenResponseBody.setEmail(user.getEmail());
            tokenResponseBody.setPerson(person);
            tokenResponseBody.setToken(token);
            tokenResponseBody.setRoles(rolesHash);

            user.setToken(token);
            userRepo.save(user);
            userToken.setUser(tokenResponseBody);

        } catch (Exception e) {
            Utils.raise(e, "Error al verificar usuario");
        }
        return userToken;


    }


    @Override
    public void recoverPassword(String email) throws AppException {
        try {
            Tuser usuario = userRepo.findByEmail(email);
            LOG.info("Usuario encontrado" + usuario);
            String newPasword = randomString(8);
            usuario.setPassword(newPasword);
        if (usuario.getEmail() == null || usuario.getStatus() == 0) {
            LOG.info("!403	User email is not found");
            throw new AppException403Forbidden(); //User email confirmation needed
        }

            String emailAddress = "sicted.oficial@gmail.com";
            String emailPassword = "SistemadeCreacionyDesarrollodeTesis";
            //Se envia el correo
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
            props.put("mail.smtp.user", emailAddress);
            props.put("mail.smtp.clave", emailPassword);    //La clave de la cuenta
            props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
            props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emailAddress));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));   //Se podrían añadir varios de la misma manera
                message.setSubject("Recuperacion de contraseña SICTED");
                message.setText("Su nueva contraseña autogenerada es " + newPasword);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", emailAddress, emailPassword);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                userRepo.save(usuario);
                LOG.info("New password solicited");
            } catch (Exception e) {
            Utils.raise(e, "Error al cambiar contraseña");
            }
        }


    //Generación de password
    public String randomString(final int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return sb.toString();
    }
}
