package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Cspecialties;
import com.integrador.sicdet.entity.Tasesor;
import com.integrador.sicdet.entity.Tperson;
import com.integrador.sicdet.repository.TasesorRepository;
import com.integrador.sicdet.service.TasesorService;
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
public class TasesorServiceImpl implements TasesorService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TasesorServiceImpl.class);

	@Autowired
	private TasesorRepository tasesorRepository;
	
	@Override
	public void insert(Tasesor tasesor ) throws Exception{
		LOGGER.debug(">>>Insert()->tasesor:{}",tasesor);
		try{
			tasesor.setCreatedAt(new Date());
			tasesor.setModifiedAt(new Date());
			tasesor.setCreatedBy(1);
			tasesor.setModifiedBy(1);
			tasesor.setStatus(1);
			LOGGER.debug(">>>Insert()->tasesor:{}",tasesor);
			tasesorRepository.save(tasesor);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tasesor: {}",id,data);
		try{
			Tasesor tasesorOptional = tasesorRepository.findById(id).get();
			if(tasesorOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tasesorOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//idPerson
			if(data.containsKey("idPerson")){
				tasesorOptional.setIdPerson(new Tperson());
				tasesorOptional.getIdPerson().setId(Integer.parseInt(data.get("idPerson").toString()));
			}
			//idSpecialties
			if(data.containsKey("idSpecialties")){
				tasesorOptional.setIdSpecialties(new Cspecialties());
				tasesorOptional.getIdSpecialties().setId((Integer)data.get("idSpecialties"));
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tasesorOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tasesorOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				tasesorOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tasesorOptional.setModifiedBy(modifiedBy);
			}
			tasesorRepository.save(tasesorOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tasesor tasesorOptional = tasesorRepository.findById(id).get();
			if(tasesorOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tasesorOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tasesorOptional.setStatus(0);
			tasesorRepository.save(tasesorOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tasesor> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tasesor>tasesorList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tasesorList = tasesorRepository.findAllActive(pageable);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tasesorList: {}",tasesorList);
		return tasesorList;
	}

}
