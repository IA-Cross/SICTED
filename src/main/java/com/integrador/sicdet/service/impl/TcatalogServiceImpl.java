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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
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
			tcatalog.setCreatedAt(new Date());
			tcatalog.setModifiedAt(new Date());
			tcatalog.setCreatedBy(1);
			tcatalog.setModifiedBy(1);
			tcatalog.setStatus(1);
			LOGGER.debug(">>>Insert()->tcatalog:{}",tcatalog);
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
			Tcatalog tcatalogOptional = tcatalogRepository.findById(id).get();
			if(tcatalogOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tcatalogOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//catalog
			if(data.containsKey("catalog")){
				String catalog = data.get("catalog").toString();
				tcatalogOptional.setCatalog(catalog);
			}
			//code
			if(data.containsKey("code")){
				String code = data.get("code").toString();
				tcatalogOptional.setCode(code);
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				tcatalogOptional.setDescription(description);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tcatalogOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tcatalogOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				tcatalogOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tcatalogOptional.setModifiedBy(modifiedBy);
			}
			tcatalogRepository.save(tcatalogOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tcatalog tcatalogOptional = tcatalogRepository.findById(id).get();
			if(tcatalogOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tcatalogOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tcatalogOptional.setStatus(0);
			tcatalogRepository.save(tcatalogOptional);
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
	
	@Override
	public List<Tcatalog> findCatalogByCode(String code) throws Exception {
		LOGGER.debug(">>>> findCatalogByCode <<<< catalog: {}",code);
		List<Tcatalog>tcatalogList=null;
		try {
			tcatalogList = tcatalogRepository.findCatalogByCode(code);
		} catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findCatalogByCode <<<< tcatalogList: {}",tcatalogList);
		return tcatalogList;
	}

}
