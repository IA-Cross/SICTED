package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.Tcorrection;
import java.util.List;
import java.util.Map;
public interface TcorrectionService{
	void insert(Tcorrection tcorrection) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tcorrection> findAll(int page,int size) throws Exception;
	List<Tcorrection>getCorrectionsByIdTesis(int page,int size,int idTesis)throws Exception;
}
