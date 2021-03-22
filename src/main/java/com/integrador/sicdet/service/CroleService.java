package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Crole;
import java.util.List;
import java.util.Map;
public interface CroleService{
	void insert(Crole crole) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Crole> findAll(int page,int size) throws Exception;
}
