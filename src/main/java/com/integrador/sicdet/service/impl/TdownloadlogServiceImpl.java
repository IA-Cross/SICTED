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
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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
			Optional<Tdownloadlog> tdownloadlogOptional = tdownloadlogRepository.findById(id);
			if(!tdownloadlogOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//ttesisId
			if(data.containsKey("ttesisId")){
				tdownloadlogOptional.get().setTtesisId(new Ttesis());
				tdownloadlogOptional.get().getTtesisId().setId((Integer)data.get("ttesisId"));
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tdownloadlogOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tdownloadlogOptional.get().setCreatedBy(createdBy);
			}
			tdownloadlogRepository.save(tdownloadlogOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tdownloadlog> tdownloadlogOptional = tdownloadlogRepository.findById(id);
			if(!tdownloadlogOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tdownloadlogRepository.delete(tdownloadlogOptional.get());
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
