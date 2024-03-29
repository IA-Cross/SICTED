package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.TcorrectionDates;
import com.integrador.sicdet.entity.Ttesis;
import com.integrador.sicdet.repository.TcorrectionDatesRepository;
import com.integrador.sicdet.service.TcorrectionDatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TcorrectionDatesServiceImpl implements TcorrectionDatesService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TcorrectionDatesServiceImpl.class);

	@Autowired
	private TcorrectionDatesRepository tcorrectionDatesRepository;

	@Override
	public void insert(TcorrectionDates tcorrectionDates ) throws Exception{
		LOGGER.debug(">>>Insert()->tcorrectionDates:{}",tcorrectionDates);
		try{
			tcorrectionDates.setId(0);
			tcorrectionDates.setCreatedAt(new Date());
			tcorrectionDates.setModifiedAt(new Date());
			tcorrectionDates.setCreatedBy(1);
			tcorrectionDates.setModifiedBy(1);
			tcorrectionDates.setStatus(1);
			LOGGER.debug(">>>Insert()->tcorrectionDates:{}",tcorrectionDates);
			tcorrectionDatesRepository.save(tcorrectionDates);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tcorrectionDates: {}",id,data);
		try{
			TcorrectionDates tcorrectionDatesOptional = tcorrectionDatesRepository.findById(id).get();
			if(tcorrectionDatesOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tcorrectionDatesOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//idTesis
			if(data.containsKey("idTesis")){
				tcorrectionDatesOptional.setIdTesis(new Ttesis());
				tcorrectionDatesOptional.getIdTesis().setId((Integer)data.get("idTesis"));
			}
			//date
			if(data.containsKey("date")){
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("date"));
				tcorrectionDatesOptional.setDate(date);
			}
			//title
			if(data.containsKey("title")){
				String title = data.get("title").toString();
				tcorrectionDatesOptional.setTitle(title);
			}
			//place
			if(data.containsKey("place")){
				String place = data.get("place").toString();
				tcorrectionDatesOptional.setPlace(place);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tcorrectionDatesOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tcorrectionDatesOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tcorrectionDatesOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tcorrectionDatesOptional.setModifiedBy(modifiedBy);
			}
			tcorrectionDatesRepository.save(tcorrectionDatesOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			TcorrectionDates tcorrectionDatesOptional = tcorrectionDatesRepository.findById(id).get();
			if(tcorrectionDatesOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tcorrectionDatesOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tcorrectionDatesOptional.setStatus(0);
			tcorrectionDatesRepository.save(tcorrectionDatesOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<TcorrectionDates> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<TcorrectionDates>tcorrectionDatesList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tcorrectionDatesList = tcorrectionDatesRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tcorrectionDatesList: {}",tcorrectionDatesList);
		return tcorrectionDatesList;
	}

}
