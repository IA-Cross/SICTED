package com.integrador.sicdet.service;
import com.integrador.sicdet.entity.TcorrectionDates;
import java.util.List;
import java.util.Map;
public interface TcorrectionDatesService{
	void insert(TcorrectionDates tcorrectionDates) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<TcorrectionDates> findAll(int page,int size) throws Exception;
}
