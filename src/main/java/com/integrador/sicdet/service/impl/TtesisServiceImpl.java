package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Ttesis;
import com.integrador.sicdet.repository.TtesisRepository;
import com.integrador.sicdet.service.TtesisService;
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
public class TtesisServiceImpl implements TtesisService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TtesisServiceImpl.class);

	@Autowired
	private TtesisRepository ttesisRepository;

	@Override
	public void insert(Ttesis ttesis ) throws Exception{
		LOGGER.debug(">>>Insert()->ttesis:{}",ttesis);
		try{
			ttesisRepository.save(ttesis);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, ttesis: {}",id,data);
		try{
			Optional<Ttesis> ttesisOptional = ttesisRepository.findById(id);
			if(!ttesisOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//idAsesor
			if(data.containsKey("idAsesor")){
				Integer idAsesor = (Integer)data.get("idAsesor");
				ttesisOptional.get().setIdAsesor(idAsesor);
			}
			//idCatDegree
			if(data.containsKey("idCatDegree")){
				Integer idCatDegree = (Integer)data.get("idCatDegree");
				ttesisOptional.get().setIdCatDegree(idCatDegree);
			}
			//idTcatalog
			if(data.containsKey("idTcatalog")){
				Integer idTcatalog = (Integer)data.get("idTcatalog");
				ttesisOptional.get().setIdTcatalog(idTcatalog);
			}
			//yearStart
			if(data.containsKey("yearStart")){
				Date yearStart = (Date)data.get("yearStart");
				ttesisOptional.get().setYearStart(yearStart);
			}
			//keywords
			if(data.containsKey("keywords")){
				String keywords = data.get("keywords").toString();
				ttesisOptional.get().setKeywords(keywords);
			}
			//categoria
			if(data.containsKey("categoria")){
				String categoria = data.get("categoria").toString();
				ttesisOptional.get().setCategoria(categoria);
			}
			//url
			if(data.containsKey("url")){
				String url = data.get("url").toString();
				ttesisOptional.get().setUrl(url);
			}
			//isPublished
			if(data.containsKey("isPublished")){
				Integer isPublished = (Integer)data.get("isPublished");
				ttesisOptional.get().setIsPublished(isPublished);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				ttesisOptional.get().setStatus(status);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				ttesisOptional.get().setCreatedBy(createdBy);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				ttesisOptional.get().setCreatedAt(createdAt);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				ttesisOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				ttesisOptional.get().setModifiedBy(modifiedBy);
			}
			ttesisRepository.save(ttesisOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Ttesis> ttesisOptional = ttesisRepository.findById(id);
			if(!ttesisOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			ttesisRepository.delete(ttesisOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Ttesis> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Ttesis>ttesisList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			ttesisList = ttesisRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< ttesisList: {}",ttesisList);
		return ttesisList;
	}

}
