package com.integrador.sicdet.entity;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
@Entity
@Table(name= "ttesis")
@NamedQueries({
		@NamedQuery(name = "Ttesis.findAllActive",query = "select u from Ttesis u where u.status=1 and u.isPublished=1 order by u.modifiedAt"),
		@NamedQuery(name = "Ttesis.searchTesis",query = "select u from Ttesis u where u.status=1 and u.idAsesor.id=:advisor and (u.title like :title) and u.isPublished=1 order by u.title"),
		@NamedQuery(name = "Ttesis.findByIdActive",query = "select u from Ttesis u where u.status=1 and u.id=:id"),
		@NamedQuery(name = "Ttesis.findByAdvisor",query = "select u from Ttesis u where u.status=1 and u.idAsesor.id=:id and u.isPublished=1 order by u.title"),
		@NamedQuery(name = "Ttesis.findByTitle",query = "select u from Ttesis u where u.status=1 and (u.title like :title) and u.isPublished=1 order by u.title"),
		@NamedQuery(name = "Ttesis.findById", query = "select u from Ttesis u where u.status = 1 and (u.id = :id)"),
		@NamedQuery(name = "Ttesis.searchTesisByTitle", query = "select u from Ttesis u where u.status = 1 and (u.title = :titulo)"),
		@NamedQuery(name = "Ttesis.findByIdAsesor", query = "select u from Ttesis u where u.status = 1 and u.idAsesor.id = :id"),

})
public class Ttesis implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_asesor",referencedColumnName = "id")
	private Tasesor idAsesor;
	@Column(name = "id_cat_degree")
	private Integer idCatDegree;
	@Column(name = "id_tcatalog")
	private Integer idTcatalog;
	@Column(name = "title")
	private String title;
	@Column(name = "year_start")
	private Date yearStart;
	@Column(name = "keywords")
	private String keywords;
	@Column(name = "categoria")
	private String categoria;
	@Column(name = "url")
	private String url;
	@Column(name = "is_published")
	private Integer isPublished;
	@Column(name = "status")
	private Integer status;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "created_at")
	private Date createdAt;
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

	public Tasesor getIdAsesor() { return idAsesor; }

	public void setIdAsesor(Tasesor idAsesor) { this.idAsesor = idAsesor; }

	public Integer getIdCatDegree(){
		 return idCatDegree;
	}

	public void setIdCatDegree(Integer idCatDegree){
		  this.idCatDegree=idCatDegree;
	}

	public Integer getIdTcatalog(){
		 return idTcatalog;
	}

	public void setIdTcatalog(Integer idTcatalog){
		  this.idTcatalog=idTcatalog;
	}

	public Date getYearStart(){
		 return yearStart;
	}

	public void setYearStart(Date yearStart){
		  this.yearStart=yearStart;
	}

	public String getKeywords(){
		 return keywords;
	}

	public void setKeywords(String keywords){
		  this.keywords=keywords;
	}

	public String getCategoria(){
		 return categoria;
	}

	public void setCategoria(String categoria){
		  this.categoria=categoria;
	}

	public String getUrl(){
		 return url;
	}

	public void setUrl(String url){
		  this.url=url;
	}

	public Integer getIsPublished(){
		 return isPublished;
	}

	public void setIsPublished(Integer isPublished){
		  this.isPublished=isPublished;
	}

	public Integer getStatus(){
		 return status;
	}

	public void setStatus(Integer status){
		  this.status=status;
	}

	public Integer getCreatedBy(){
		 return createdBy;
	}

	public void setCreatedBy(Integer createdBy){
		  this.createdBy=createdBy;
	}

	public Date getCreatedAt(){
		 return createdAt;
	}

	public void setCreatedAt(Date createdAt){
		  this.createdAt=createdAt;
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

	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }

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
