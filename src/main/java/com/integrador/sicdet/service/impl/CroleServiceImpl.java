package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Crole;
import com.integrador.sicdet.repository.CroleRepository;
import com.integrador.sicdet.service.CroleService;
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
public class CroleServiceImpl implements CroleService{


	private static final Logger LOGGER = LoggerFactory.getLogger(CroleServiceImpl.class);

	@Autowired
	private CroleRepository croleRepository;

	@Override
	public void insert(Crole crole ) throws Exception{
		LOGGER.debug(">>>Insert()->crole:{}",crole);
		try{
			crole.setCreatedAt(new Date());
			crole.setModifiedAt(new Date());
			crole.setCreatedBy(1);
			crole.setModifiedBy(1);
			crole.setStatus(1);
			LOGGER.debug(">>>Insert()->crole:{}",crole);
			croleRepository.save(crole);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, crole: {}",id,data);
		try{
			Optional<Crole> croleOptional = croleRepository.findById(id);
			if(!croleOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//code
			if(data.containsKey("code")){
				String code = data.get("code").toString();
				croleOptional.get().setCode(code);
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				croleOptional.get().setDescription(description);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				croleOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				croleOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				croleOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				croleOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				croleOptional.get().setModifiedBy(modifiedBy);
			}
			croleRepository.save(croleOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Crole croleOptional = croleRepository.findById(id).get();
			if(croleOptional == null){
				throw new Exception("No existe el registro");
			}
			if(croleOptional.getStatus() == 0){
				throw new Exception("No se encuentra el registro");
			}
			croleOptional.setStatus(0);
			croleRepository.save(croleOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Crole> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Crole>croleList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			croleList = croleRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< croleList: {}",croleList);
		return croleList;
	}

}
