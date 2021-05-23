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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
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
			tuserrole.setCreatedAt(new Date());
			tuserrole.setModifiedAt(new Date());
			tuserrole.setCreatedBy(1);
			tuserrole.setModifiedBy(1);
			tuserrole.setStatus(1);
			LOGGER.debug(">>>Insert()->tuserrole:{}",tuserrole);
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
			Tuserrole tuserroleOptional = tuserroleRepository.findById(id).get();
			if(tuserroleOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tuserroleOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//iduser
			if(data.containsKey("iduser")){
				tuserroleOptional.setIduser(new Tuser());
				tuserroleOptional.getIduser().setId((Integer)data.get("iduser"));
			}
			//idrol
			if(data.containsKey("idrol")){
				tuserroleOptional.setIdrol(new Crole());
				tuserroleOptional.getIdrol().setId((Integer)data.get("idrol"));
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tuserroleOptional.setCreatedAt(createdAt);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				tuserroleOptional.setModifiedAt(modifiedAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tuserroleOptional.setCreatedBy(createdBy);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tuserroleOptional.setModifiedBy(modifiedBy);
			}
			tuserroleRepository.save(tuserroleOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tuserrole tuserroleOptional = tuserroleRepository.findById(id).get();
			if(tuserroleOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tuserroleOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tuserroleOptional.setStatus(0);
			tuserroleRepository.save(tuserroleOptional);
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
