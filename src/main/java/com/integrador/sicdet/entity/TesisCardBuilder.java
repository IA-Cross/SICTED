package com.integrador.sicdet.entity;

import org.springframework.beans.factory.annotation.Autowired;


public class TesisCardBuilder {

    private TesisCardBuilder(){

    }

    public static TesisCardFormat fromTesis(Ttesis tesis, String author){
        Ttesis origin = tesis;
        TesisCardFormat destin = new TesisCardFormat();

        destin.setTitle(origin.getTitle());
        destin.setSubtitle(author);
        destin.setLink(origin.getUrl());
        destin.setText(origin.getCategoria());

        return destin;
    }
}
