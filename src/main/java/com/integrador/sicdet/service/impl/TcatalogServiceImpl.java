package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tcatalog;
import com.integrador.sicdet.repository.TcatalogRepository;
import com.integrador.sicdet.service.TcatalogService;
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
public class TcatalogServiceImpl implements TcatalogService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TcatalogServiceImpl.class);

	@Autowired
	private TcatalogRepository tcatalogRepository;

	@Override
	public void insert(Tcatalog tcatalog ) throws Exception{
		LOGGER.debug(">>>Insert()->tcatalog:{}",tcatalog);
		try{
			tcatalogRepository.save(tcatalog);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tcatalog: {}",id,data);
		try{
			Optional<Tcatalog> tcatalogOptional = tcatalogRepository.findById(id);
			if(!tcatalogOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//catalog
			if(data.containsKey("catalog")){
				String catalog = data.get("catalog").toString();
				tcatalogOptional.get().setCatalog(catalog);
			}
			//code
			if(data.containsKey("code")){
				String code = data.get("code").toString();
				tcatalogOptional.get().setCode(code);
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				tcatalogOptional.get().setDescription(description);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tcatalogOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tcatalogOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tcatalogOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tcatalogOptional.get().setModifiedBy(modifiedBy);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tcatalogOptional.get().setStatus(status);
			}
			tcatalogRepository.save(tcatalogOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tcatalog> tcatalogOptional = tcatalogRepository.findById(id);
			if(!tcatalogOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tcatalogRepository.delete(tcatalogOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tcatalog> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tcatalog>tcatalogList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tcatalogList = tcatalogRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tcatalogList: {}",tcatalogList);
		return tcatalogList;
	}

}
