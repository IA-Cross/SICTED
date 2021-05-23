package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Cspecialties;
import com.integrador.sicdet.repository.CspecialtiesRepository;
import com.integrador.sicdet.service.CspecialtiesService;
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
public class CspecialtiesServiceImpl implements CspecialtiesService{


	private static final Logger LOGGER = LoggerFactory.getLogger(CspecialtiesServiceImpl.class);

	@Autowired
	private CspecialtiesRepository cspecialtiesRepository;

	@Override
	public void insert(Cspecialties cspecialties ) throws Exception{
		LOGGER.debug(">>>Insert()->cspecialties:{}",cspecialties);
		try{
			cspecialties.setCreatedAt(new Date());
			cspecialties.setModifiedAt(new Date());
			cspecialties.setCreatedBy(1);
			cspecialties.setModifiedBy(1);
			cspecialties.setStatus(1);
			LOGGER.debug(">>>Insert()->cspecialties:{}",cspecialties);
			cspecialtiesRepository.save(cspecialties);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, cspecialties: {}",id,data);
		try{
			Cspecialties cspecialtiesOptional = cspecialtiesRepository.findById(id).get();
			if(cspecialtiesOptional == null){
				throw new Exception("No existe el registro");
			}
			if(cspecialtiesOptional.getStatus() == 0){
				throw new Exception("No se encuentra el registro");
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				cspecialtiesOptional.setDescription(description);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				cspecialtiesOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				cspecialtiesOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				cspecialtiesOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				cspecialtiesOptional.setModifiedBy(modifiedBy);
			}
			cspecialtiesRepository.save(cspecialtiesOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Cspecialties cspecialtiesOptional = cspecialtiesRepository.findById(id).get();
			if(cspecialtiesOptional == null){
				throw new Exception("No existe el registro");
			}
			if(cspecialtiesOptional.getStatus() == 0){
				throw new Exception("No se encuentra el registro");
			}
			cspecialtiesOptional.setStatus(0);
			cspecialtiesRepository.save(cspecialtiesOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Cspecialties> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Cspecialties>cspecialtiesList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			cspecialtiesList = cspecialtiesRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< cspecialtiesList: {}",cspecialtiesList);
		return cspecialtiesList;
	}

}
