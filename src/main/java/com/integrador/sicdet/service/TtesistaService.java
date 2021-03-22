package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Ttesista;
import java.util.List;
import java.util.Map;
public interface TtesistaService{
	void insert(Ttesista ttesista) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Ttesista> findAll(int page,int size) throws Exception;
}
