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
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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
			Optional<Cspecialties> cspecialtiesOptional = cspecialtiesRepository.findById(id);
			if(!cspecialtiesOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				cspecialtiesOptional.get().setDescription(description);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				cspecialtiesOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				cspecialtiesOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				cspecialtiesOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				cspecialtiesOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				cspecialtiesOptional.get().setModifiedBy(modifiedBy);
			}
			cspecialtiesRepository.save(cspecialtiesOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Cspecialties> cspecialtiesOptional = cspecialtiesRepository.findById(id);
			if(!cspecialtiesOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			cspecialtiesRepository.delete(cspecialtiesOptional.get());
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
