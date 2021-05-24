package com.integrador.sicdet.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Map;

public class TuserWithRolesFormat {
    private Integer id;
    private Tperson idperson;
    private String email;
    private String password;
    private Integer status;
    private String token;
    private String roles;
    private Date createdAt;
    private Integer createdBy;
    private Date modifiedAt;
    private Integer modifiedBy;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public Tperson getIdperson() { return idperson; }

    public void setIdperson(Tperson idperson) { this.idperson = idperson; }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getRoles() { return roles; }

    public void setRoles(String roles) { this.roles = roles; }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status=status;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt=createdAt;
    }

    public Integer getCreatedBy(){
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy){
        this.createdBy=createdBy;
    }

    public Date getModifiedAt(){
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt){
        this.modifiedAt=modifiedAt;
    }

    public Integer getModifiedBy(){
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy){
        this.modifiedBy=modifiedBy;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }
}
