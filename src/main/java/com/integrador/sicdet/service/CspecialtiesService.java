package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Cspecialties;
import java.util.List;
import java.util.Map;
public interface CspecialtiesService{
	void insert(Cspecialties cspecialties) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Cspecialties> findAll(int page,int size) throws Exception;
}
