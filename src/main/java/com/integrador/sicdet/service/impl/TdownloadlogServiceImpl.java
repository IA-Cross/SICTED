package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tdownloadlog;
import com.integrador.sicdet.entity.Ttesis;
import com.integrador.sicdet.repository.TdownloadlogRepository;
import com.integrador.sicdet.service.TdownloadlogService;
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
public class TdownloadlogServiceImpl implements TdownloadlogService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TdownloadlogServiceImpl.class);

	@Autowired
	private TdownloadlogRepository tdownloadlogRepository;

	@Override
	public void insert(Tdownloadlog tdownloadlog ) throws Exception{
		LOGGER.debug(">>>Insert()->tdownloadlog:{}",tdownloadlog);
		try{
			tdownloadlog.setCreatedAt(new Date());
			tdownloadlog.setCreatedBy(1);
			LOGGER.debug(">>>Insert()->tasesor:{}",tdownloadlog);
			tdownloadlogRepository.save(tdownloadlog);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tdownloadlog: {}",id,data);
		try{
			Tdownloadlog tdownloadlogOptional = tdownloadlogRepository.findById(id).get();
			if(tdownloadlogOptional == null){
				throw new Exception("No existe el registro");
			}
			//ttesisId
			if(data.containsKey("ttesisId")){
				tdownloadlogOptional.setTtesisId(new Ttesis());
				tdownloadlogOptional.getTtesisId().setId((Integer)data.get("ttesisId"));
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tdownloadlogOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tdownloadlogOptional.setCreatedBy(createdBy);
			}
			tdownloadlogRepository.save(tdownloadlogOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	
	@Override
	public List<Tdownloadlog> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tdownloadlog>tdownloadlogList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tdownloadlogList = tdownloadlogRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tdownloadlogList: {}",tdownloadlogList);
		return tdownloadlogList;
	}

}
