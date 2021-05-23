package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.*;
import com.integrador.sicdet.repository.CroleRepository;
import com.integrador.sicdet.repository.TuserRepository;
import com.integrador.sicdet.repository.TuserroleRepository;
import com.integrador.sicdet.service.TuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TuserServiceImpl implements TuserService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TuserServiceImpl.class);

	@Autowired
	private TuserRepository tuserRepository;
	@Autowired
	private TuserroleRepository userRole;
	@Autowired
	private CroleRepository role;

	@Override
	public void insert(Tuser tuser ) throws Exception{
		LOGGER.debug(">>>Insert()->tuser:{}",tuser);
		try{
			tuser.setCreatedAt(new Date());
			tuser.setModifiedAt(new Date());
			tuser.setCreatedBy(1);
			tuser.setModifiedBy(1);
			tuser.setStatus(1);
			LOGGER.debug(">>>Insert()->tuser:{}",tuser);
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
			Tuser tuserOptional = tuserRepository.findById(id).get();
			if(tuserOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tuserOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//idperson
			if(data.containsKey("idperson")){
				tuserOptional.setIdperson(new Tperson());
				tuserOptional.getIdperson().setId((Integer)data.get("idperson"));
			}
			//email
			if(data.containsKey("email")){
				String email = data.get("email").toString();
				tuserOptional.setEmail(email);
			}
			//password
			if(data.containsKey("password")){
				String password = data.get("password").toString();
				tuserOptional.setPassword(password);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tuserOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tuserOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				tuserOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tuserOptional.setModifiedBy(modifiedBy);
			}
			tuserRepository.save(tuserOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}", id);
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
	public List<TuserWithRolesFormat> findAll(int page, int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}", page, size);
		List<Tuser>tuserList=null;
		List<TuserWithRolesFormat>users= new ArrayList<>();
		List<Tuserrole> userRoles = null;
		Crole ole=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tuserList = tuserRepository.findAllActive(pageable);
			for (Tuser user:tuserList){
				userRoles = userRole.findAllByIdUser(user.getId());
				for (int i=0;i<userRoles.size();i++) {
					int id=userRoles.get(i).getIdrol().getId();
					 ole = role.findById(id);
					System.out.println("ROL ENCONTRADO "+ole.getDescription());
				}//Guardamos los roles para despues
				users.add(TuserWithRolesBuilder.fromTuser(user,ole.getDescription()));
			}

		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tuserList: {}",tuserList);
		return users;
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
