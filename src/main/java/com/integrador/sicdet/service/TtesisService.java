package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Ttesis;
import java.util.List;
import java.util.Map;
public interface TtesisService{
	void insert(Ttesis ttesis) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Ttesis> findAll(int page,int size) throws Exception;
	List<Ttesis> searchTesis(int page,int size,Map<String,Object> data) throws Exception;
}
