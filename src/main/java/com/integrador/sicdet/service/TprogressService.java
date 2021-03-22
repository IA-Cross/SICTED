package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tprogress;
import java.util.List;
import java.util.Map;
public interface TprogressService{
	void insert(Tprogress tprogress) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tprogress> findAll(int page,int size) throws Exception;
}
