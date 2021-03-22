package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tuser;
import java.util.List;
import java.util.Map;
public interface TuserService{
	void insert(Tuser tuser) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tuser> findAll(int page,int size) throws Exception;
}
