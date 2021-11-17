package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tperson;
import com.integrador.sicdet.entity.Tuser;
import com.integrador.sicdet.repository.TpersonRepository;
import com.integrador.sicdet.repository.TuserRepository;
import com.integrador.sicdet.service.TpersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TpersonServiceImpl implements TpersonService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TpersonServiceImpl.class);

	@Autowired
	private TpersonRepository tpersonRepository;
	@Autowired
	private TuserRepository usersRepo;

	@Override
	public void insert(Tperson tperson ) throws Exception{
		try{
			tperson.setId(0);
			tperson.setCreatedAt(new Date());
			tperson.setModifiedAt(new Date());
			tperson.setCreatedBy(1);
			tperson.setModifiedBy(1);
			tperson.setStatus(1);
			LOGGER.debug(">>>Insert()->tperson:{}",tperson);
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
			Tperson tpersonOptional = tpersonRepository.findById(id).get();
			if(tpersonOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tpersonOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//name
			if(data.containsKey("name")){
				String name = data.get("name").toString();
				tpersonOptional.setName(name);
			}
			//firstlastname
			if(data.containsKey("firstlastname")){
				String firstlastname = data.get("firstlastname").toString();
				tpersonOptional.setFirstlastname(firstlastname);
			}
			//secondlastname
			if(data.containsKey("secondlastname")){
				String secondlastname = data.get("secondlastname").toString();
				tpersonOptional.setSecondlastname(secondlastname);
			}
			//gender
			if(data.containsKey("gender")){
				char gender = data.get("gender").toString().charAt(0);
				tpersonOptional.setGender(gender);
			}
			//birthdate
			if(data.containsKey("birthdate")){
				Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("birthdate"));
				tpersonOptional.setBirthdate(birthdate);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tpersonOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tpersonOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				tpersonOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tpersonOptional.setModifiedBy(modifiedBy);
			}
			tpersonRepository.save(tpersonOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tperson tpersonOptional = tpersonRepository.findById(id).get();
			if(tpersonOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tpersonOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tpersonOptional.setStatus(0);
			tpersonRepository.save(tpersonOptional);
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
			tpersonList = tpersonRepository.findAllActive(pageable);

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

	@Override
	public List<Tperson> searchPerson(String name) throws Exception {
		LOGGER.debug(">>>>>name: {}<<<<<<",name);
		List<Tperson>finded=new ArrayList<>();
		try{
			name=name.replace(" ", "%");
			finded= tpersonRepository.searchByName("%"+name+"%");
		}catch(Exception e){
			LOGGER.error("Exception: {}",e);
		}
		return finded;
	}

	@Override
	public List<Tperson> personsWithoutUser() throws Exception {
		List<Tperson> res=new ArrayList<>();
		List<Tperson> finded=null;
		List<Tuser> users= null;
		boolean findedFlag=false;
		try {
			users=usersRepo.findAll();
			finded= tpersonRepository.findAll();
			for(Tperson person: finded){
				findedFlag=false;
				for (Tuser user : users) {
					if (person.getId().equals(user.getIdperson().getId())) {
						findedFlag = true;
						break;
					}
				}
				if(!findedFlag)
				res.add(person);
			}

		}catch(Exception e){
			LOGGER.error("Exception: {}",e);
		}

		return res;
	}

}
