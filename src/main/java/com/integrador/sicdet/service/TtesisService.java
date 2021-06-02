package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.TesisCardFormat;
import com.integrador.sicdet.entity.Ttesis;
import java.util.List;
import java.util.Map;
public interface TtesisService{
	void insert(Ttesis ttesis) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Ttesis> findAll() throws Exception;
	List<Ttesis> searchTesis(int page,int size,Map<String,Object> data) throws Exception;
	List<TesisCardFormat> findAllCardFormat() throws Exception;
	Ttesis findById(int id) throws Exception;
	Ttesis searchTesisByTitle(String titulo) throws Exception;
	List<Ttesis> findAdvisedTesis (int id);
	void updateUrl(Integer id, String url) throws Exception;
}
