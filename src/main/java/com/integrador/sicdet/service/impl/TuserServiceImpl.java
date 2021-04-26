package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tperson;
import com.integrador.sicdet.entity.Tuser;
import com.integrador.sicdet.repository.TuserRepository;
import com.integrador.sicdet.service.TuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TuserServiceImpl implements TuserService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TuserServiceImpl.class);

	@Autowired
	private TuserRepository tuserRepository;

	@Override
	public void insert(Tuser tuser ) throws Exception{
		LOGGER.debug(">>>Insert()->tuser:{}",tuser);
		try{
			tuserRepository.save(tuser);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tuser: {}",id,data);
		try{
			Optional<Tuser> tuserOptional = tuserRepository.findById(id);
			if(!tuserOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//idperson
			if(data.containsKey("idperson")){
				tuserOptional.get().setIdperson(new Tperson());
				tuserOptional.get().getIdperson().setId((Integer)data.get("idperson"));
			}
			//email
			if(data.containsKey("email")){
				String email = data.get("email").toString();
				tuserOptional.get().setEmail(email);
			}
			//password
			if(data.containsKey("password")){
				String password = data.get("password").toString();
				tuserOptional.get().setPassword(password);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tuserOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tuserOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tuserOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tuserOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tuserOptional.get().setModifiedBy(modifiedBy);
			}
			tuserRepository.save(tuserOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tuser tuserOptional = tuserRepository.findById(id).get();
			if(tuserOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tuserOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tuserOptional.setStatus(0);
			tuserRepository.save(tuserOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tuser> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tuser>tuserList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tuserList = tuserRepository.findAllActive(pageable);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tuserList: {}",tuserList);
		return tuserList;
	}
	
	@Override
	public Tuser findUserById(int id) throws Exception {
		Tuser res = new Tuser();
		try {
			res = tuserRepository.findUserById(id);
		} catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		return res;		
	}

	@Override
	public List<Tuser> searchUser(String name) throws Exception {
		List<Tuser>finded=new ArrayList<>();
		try{
			name=name.replace(" ", "%");
			finded= tuserRepository.searchByName("%"+name+"%");

		}catch(Exception e){
			LOGGER.error("Exception: {}",e);
		}
		return finded;
	}

}
