package com.integrador.sicdet.entity;

import java.util.Map;

public class TuserWithRolesBuilder {
    private TuserWithRolesBuilder(){

    }

    public static TuserWithRolesFormat fromTuser(Tuser user, Map<String,String> roles){
        Tuser origin = user;
        TuserWithRolesFormat destin = new TuserWithRolesFormat();
        destin.setId(origin.getId());
        destin.setIdperson(origin.getIdperson());
        destin.setIdperson(origin.getIdperson());
        destin.setEmail(origin.getEmail());
        destin.setToken(origin.getToken());
        destin.setStatus(origin.getStatus());
        destin.setCreatedAt(origin.getCreatedAt());
        destin.setCreatedBy(origin.getCreatedBy());
        destin.setModifiedAt(origin.getModifiedAt());
        destin.setModifiedBy(origin.getModifiedBy());
        destin.setRoles(roles);

        return destin;
    }
}
