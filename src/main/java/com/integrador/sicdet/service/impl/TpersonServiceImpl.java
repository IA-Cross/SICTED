package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tperson;
import com.integrador.sicdet.repository.TpersonRepository;
import com.integrador.sicdet.service.TpersonService;
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
public class TpersonServiceImpl implements TpersonService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TpersonServiceImpl.class);

	@Autowired
	private TpersonRepository tpersonRepository;

	@Override
	public void insert(Tperson tperson ) throws Exception{
		LOGGER.debug(">>>Insert()->tperson:{}",tperson);
		try{
			tpersonRepository.save(tperson);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tperson: {}",id,data);
		try{
			Optional<Tperson> tpersonOptional = tpersonRepository.findById(id);
			if(!tpersonOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//name
			if(data.containsKey("name")){
				String name = data.get("name").toString();
				tpersonOptional.get().setName(name);
			}
			//firstlastname
			if(data.containsKey("firstlastname")){
				String firstlastname = data.get("firstlastname").toString();
				tpersonOptional.get().setFirstlastname(firstlastname);
			}
			//secondlastname
			if(data.containsKey("secondlastname")){
				String secondlastname = data.get("secondlastname").toString();
				tpersonOptional.get().setSecondlastname(secondlastname);
			}
			//gender
			if(data.containsKey("gender")){
				String gender = data.get("gender").toString();
				tpersonOptional.get().setGender(gender);
			}
			//birthdate
			if(data.containsKey("birthdate")){
				Date birthdate = (Date)data.get("birthdate");
				tpersonOptional.get().setBirthdate(birthdate);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tpersonOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tpersonOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tpersonOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tpersonOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tpersonOptional.get().setModifiedBy(modifiedBy);
			}
			tpersonRepository.save(tpersonOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tperson> tpersonOptional = tpersonRepository.findById(id);
			if(!tpersonOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tpersonRepository.delete(tpersonOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tperson> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tperson>tpersonList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tpersonList = tpersonRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tpersonList: {}",tpersonList);
		return tpersonList;
	}

	@Override
	public List<Tperson> findPersonjTest() throws Exception {
		LOGGER.debug(">>>> findPersonjTest <<<<");
		List<Tperson>tpersonList=null;
		try{
			tpersonList=tpersonRepository.findPersonjTest();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tpersonList: {}",tpersonList);
		return tpersonList;
	}

}
