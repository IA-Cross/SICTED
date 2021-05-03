package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Cspecialties;
import com.integrador.sicdet.entity.Tasesor;
import com.integrador.sicdet.entity.Tperson;
import com.integrador.sicdet.repository.TasesorRepository;
import com.integrador.sicdet.repository.TpersonRepository;
import com.integrador.sicdet.service.TasesorService;
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
public class TasesorServiceImpl implements TasesorService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TasesorServiceImpl.class);

	@Autowired
	private TasesorRepository tasesorRepository;
	@Autowired
	private TpersonRepository personRepo;

	@Override
	public void insert(Tasesor tasesor ) throws Exception{
		LOGGER.debug(">>>Insert()->tasesor:{}",tasesor);
		try{
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
			Optional<Tasesor> tasesorOptional = tasesorRepository.findById(id);
			if(!tasesorOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//idPerson
			if(data.containsKey("idPerson")){
				tasesorOptional.get().setIdPerson(new Tperson());
				tasesorOptional.get().getIdPerson().setId(Integer.parseInt(data.get("idPerson").toString()));
			}
			//idSpecialties
			if(data.containsKey("idSpecialties")){
				tasesorOptional.get().setIdSpecialties(new Cspecialties());
				tasesorOptional.get().getIdSpecialties().setId((Integer)data.get("idSpecialties"));
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tasesorOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tasesorOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tasesorOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tasesorOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tasesorOptional.get().setModifiedBy(modifiedBy);
			}
			tasesorRepository.save(tasesorOptional.get());
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
