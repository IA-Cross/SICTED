package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tasesor;
import java.util.List;
import java.util.Map;
public interface TasesorService{
	void insert(Tasesor tasesor) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tasesor> findAll(int page,int size) throws Exception;
}
