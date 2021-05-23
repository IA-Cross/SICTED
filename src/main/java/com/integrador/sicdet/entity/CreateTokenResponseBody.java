package com.integrador.sicdet.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class CreateTokenResponseBody {
    private String token;
    private String claveHash;
    private String email;
    private String otherIdentifier;
    private Map<String,String> roles;
    private Map<String,String> person;
    private Map<String,String> personExtensions;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getClaveHash() { return claveHash; }
    public void setClaveHash(String claveHash) { this.claveHash = claveHash; }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOtherIdentifier() {
        return otherIdentifier;
    }
    public void setOtherIdentifier(String otherIdentifier) {
        this.otherIdentifier = otherIdentifier;
    }
    public Map<String, String> getRoles() { return roles; }
    public void setRoles(Map<String, String> roles) { this.roles = roles; }
    public Map<String, String> getPerson() {
        return person;
    }
    public void setPerson(Map<String, String> person) {
        this.person = person;
    }
    public Map<String, String> getPersonExtensions() {
        return personExtensions;
    }
    public void setPersonExtensions(Map<String, String> personExtensions) {
        this.personExtensions = personExtensions;
    }
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
