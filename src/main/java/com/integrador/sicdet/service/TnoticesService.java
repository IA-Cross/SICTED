package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tnotices;
import java.util.List;
import java.util.Map;
public interface TnoticesService{
	void insert(Tnotices tnotices) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tnotices> findAll(int page,int size) throws Exception;
}
