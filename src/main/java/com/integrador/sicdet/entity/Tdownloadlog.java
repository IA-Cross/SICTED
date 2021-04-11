package com.integrador.sicdet.entity;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
@Entity
@Table(name= "tdownloadlog")
public class Tdownloadlog implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "ttesis_id",referencedColumnName = "id")
	private Ttesis ttesisId;
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
