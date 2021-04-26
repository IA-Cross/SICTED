package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tperson;
import com.integrador.sicdet.entity.Tuser;

import java.util.List;
import java.util.Map;
public interface TpersonService{
	void insert(Tperson tperson) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tperson> findAll(int page,int size) throws Exception;
	List<Tperson> findPersonjTest() throws Exception;
	List<Tperson> searchPerson(String name) throws Exception;
}
