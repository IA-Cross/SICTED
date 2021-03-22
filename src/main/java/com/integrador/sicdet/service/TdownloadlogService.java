package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tdownloadlog;
import java.util.List;
import java.util.Map;
public interface TdownloadlogService{
	void insert(Tdownloadlog tdownloadlog) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tdownloadlog> findAll(int page,int size) throws Exception;
}
