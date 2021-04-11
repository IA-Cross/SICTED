package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Crole;
import com.integrador.sicdet.entity.Tuser;
import com.integrador.sicdet.entity.Tuserrole;
import com.integrador.sicdet.repository.TuserroleRepository;
import com.integrador.sicdet.service.TuserroleService;
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
public class TuserroleServiceImpl implements TuserroleService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TuserroleServiceImpl.class);

	@Autowired
	private TuserroleRepository tuserroleRepository;

	@Override
	public void insert(Tuserrole tuserrole ) throws Exception{
		LOGGER.debug(">>>Insert()->tuserrole:{}",tuserrole);
		try{
			tuserroleRepository.save(tuserrole);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tuserrole: {}",id,data);
		try{
			Optional<Tuserrole> tuserroleOptional = tuserroleRepository.findById(id);
			if(!tuserroleOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//iduser
			if(data.containsKey("iduser")){
				tuserroleOptional.get().setIduser(new Tuser());
				tuserroleOptional.get().getIduser().setId((Integer)data.get("iduser"));
			}
			//idrol
			if(data.containsKey("idrol")){
				Integer idrol = (Integer)data.get("idrol");
				tuserroleOptional.get().setIdrol(new Crole());
				tuserroleOptional.get().getIdrol().setId((Integer)data.get("idrol"));
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tuserroleOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tuserroleOptional.get().setCreatedAt(createdAt);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tuserroleOptional.get().setModifiedAt(modifiedAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tuserroleOptional.get().setCreatedBy(createdBy);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tuserroleOptional.get().setModifiedBy(modifiedBy);
			}
			tuserroleRepository.save(tuserroleOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tuserrole> tuserroleOptional = tuserroleRepository.findById(id);
			if(!tuserroleOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tuserroleRepository.delete(tuserroleOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tuserrole> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tuserrole>tuserroleList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tuserroleList = tuserroleRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tuserroleList: {}",tuserroleList);
		return tuserroleList;
	}

}
