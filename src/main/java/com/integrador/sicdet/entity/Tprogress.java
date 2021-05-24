package com.integrador.sicdet.entity;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
@Entity
@Table(name= "tprogress")
@NamedQueries({
	@NamedQuery(name = "Tprogress.findAllByIdTesis", query="select tp from Tprogress tp where tp.status=1 and (tp.ttesisId.id=:idTesis) order by tp.createdAt")
})
public class Tprogress implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "ttesis_id",referencedColumnName = "id")
	private Ttesis ttesisId;
	@Column(name = "progress")
	private Integer progress;
	@Column(name = "description")
	private String description;
	@Column(name = "is_verified")
	private Integer isVerified;
	@Column(name = "status")
	private Integer status;
	@Column(name = "modified_at")
	private Date modifiedAt;
	@Column(name = "modified_by")
	private Integer modifiedBy;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "created_by")
	private Integer createdBy;

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public Ttesis getTtesisId() { return ttesisId; }

	public void setTtesisId(Ttesis ttesisId) { this.ttesisId = ttesisId; }

	public Integer getProgress(){
		 return progress;
	}

	public void setProgress(Integer progress){
		  this.progress=progress;
	}

	public String getDescription(){
		 return description;
	}

	public void setDescription(String description){
		  this.description=description;
	}

	public Integer getIsVerified(){
		 return isVerified;
	}

	public void setIsVerified(Integer isVerified){
		  this.isVerified=isVerified;
	}

	public Integer getStatus(){
		 return status;
	}

	public void setStatus(Integer status){
		  this.status=status;
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
