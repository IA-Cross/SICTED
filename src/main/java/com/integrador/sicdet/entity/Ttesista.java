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
@Table(name= "ttesista")
public class Ttesista implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "id_person")
	private Integer idPerson;
	@Column(name = "ttesis_id")
	private Integer ttesisId;
	@Column(name = "enrollment")
	private String enrollment;
	@Column(name = "id_cat_degree")
	private Integer idCatDegree;
	@Column(name = "year_start")
	private Date yearStart;
	@Column(name = "year_end")
	private Date yearEnd;
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

	public Integer getIdPerson(){
		 return idPerson;
	}

	public void setIdPerson(Integer idPerson){
		  this.idPerson=idPerson;
	}

	public Integer getTtesisId(){
		 return ttesisId;
	}

	public void setTtesisId(Integer ttesisId){
		  this.ttesisId=ttesisId;
	}

	public String getEnrollment(){
		 return enrollment;
	}

	public void setEnrollment(String enrollment){
		  this.enrollment=enrollment;
	}

	public Integer getIdCatDegree(){
		 return idCatDegree;
	}

	public void setIdCatDegree(Integer idCatDegree){
		  this.idCatDegree=idCatDegree;
	}

	public Date getYearStart(){
		 return yearStart;
	}

	public void setYearStart(Date yearStart){
		  this.yearStart=yearStart;
	}

	public Date getYearEnd(){
		 return yearEnd;
	}

	public void setYearEnd(Date yearEnd){
		  this.yearEnd=yearEnd;
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
