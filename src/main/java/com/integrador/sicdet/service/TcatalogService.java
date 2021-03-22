package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tcatalog;
import java.util.List;
import java.util.Map;
public interface TcatalogService{
	void insert(Tcatalog tcatalog) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tcatalog> findAll(int page,int size) throws Exception;
}
