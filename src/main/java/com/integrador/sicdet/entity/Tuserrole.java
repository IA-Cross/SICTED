package com.integrador.sicdet.entity;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
@Entity
@Table(name= "tuserrole")
@NamedQueries({
		@NamedQuery(name="Tuserrole.findByIdUser",query="select r from Tuserrole r where r.status=1 and r.iduser.id=:id"),
		@NamedQuery(name = "Tuserrole.findAllByIdUser", query = "select r from Tuserrole r where r.iduser.id = :id and r.status=1"),
})
public class Tuserrole implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "iduser",referencedColumnName = "id")
	private Tuser iduser;
	@ManyToOne
	@JoinColumn(name = "idrol",referencedColumnName = "id")
	private Crole idrol;
	@Column(name = "status")
	private Integer status;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "modified_at")
	private Date modifiedAt;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "modified_by")
	private Integer modifiedBy;

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public Tuser getIduser() { return iduser; }

	public void setIduser(Tuser iduser) { this.iduser = iduser; }

	public Crole getIdrol() { return idrol; }

	public void setIdrol(Crole idrol) { this.idrol = idrol; }

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

	public Date getModifiedAt(){
		 return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt){
		  this.modifiedAt=modifiedAt;
	}

	public Integer getCreatedBy(){
		 return createdBy;
	}

	public void setCreatedBy(Integer createdBy){
		  this.createdBy=createdBy;
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
