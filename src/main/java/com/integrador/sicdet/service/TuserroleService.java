package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tuserrole;
import java.util.List;
import java.util.Map;
public interface TuserroleService{
	void insert(Tuserrole tuserrole) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tuserrole> findAll(int page,int size) throws Exception;
}
