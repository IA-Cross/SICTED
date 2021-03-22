package com.integrador.sicdet.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "turl")
@NamedQueries({
        @NamedQuery(name = "Turl.findByUrl",query = "select u from Turl u where u.status=1 and u.url=:url")
})
public class Turl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private Integer isPublic;
    private String allowedRolesCommaSeparated;
    private Integer status;
    private Date createdAt;
    private Date modifiedAt;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getIsPublic() {
        return isPublic;
    }
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
    public String getAllowedRolesCommaSeparated() {
        return allowedRolesCommaSeparated;
    }
    public void setAllowedRolesCommaSeparated(String allowedRolesCommaSeparated) {
        this.allowedRolesCommaSeparated = allowedRolesCommaSeparated; }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
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
