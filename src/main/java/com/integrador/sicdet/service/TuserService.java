package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tuser;
import com.integrador.sicdet.entity.TuserWithRolesFormat;

import java.util.List;
import java.util.Map;
public interface TuserService{
	void insert(Tuser tuser) throws Exception;
	void insert2(Map<String,Object> data) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<TuserWithRolesFormat> findAll(int page, int size) throws Exception;
	Tuser findUserById(int id) throws Exception;
	List<Tuser> searchUser(String name) throws Exception;
}
