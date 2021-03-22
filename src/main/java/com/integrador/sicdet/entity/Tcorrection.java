package com.integrador.sicdet.entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
@Entity
@Table(name= "tcorrection")
public class Tcorrection implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "id_tesis")
	private Integer idTesis;
	@Column(name = "description")
	private String description;
	@Column(name = "date")
	private Date date;
	@Column(name = "text")
	private String text;
	@Column(name = "status")
	private Integer status;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "modified_at")
	private Date modifiedAt;
	@Column(name = "modified_by")
	private Integer modifiedBy;

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public Integer getIdTesis(){
		 return idTesis;
	}

	public void setIdTesis(Integer idTesis){
		  this.idTesis=idTesis;
	}

	public String getDescription(){
		 return description;
	}

	public void setDescription(String description){
		  this.description=description;
	}

	public Date getDate(){
		 return date;
	}

	public void setDate(Date date){
		  this.date=date;
	}

	public String getText(){ return text;}

	public void setText( String text){
		  this.text=text;
	}

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
