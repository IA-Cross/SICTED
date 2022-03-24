package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.*;
import com.integrador.sicdet.repository.*;
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
	@Autowired
	private TpersonRepository personRepo;
	@Autowired
	private TuserroleRepository roleRepo;
	@Autowired
	private TtesistaRepository tesistaRepo;

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
	public void insert2(Map<String, Object> data) throws Exception {
		Tuser user = new Tuser();
		Tperson tperson = new Tperson();
		Tuserrole role = new Tuserrole();
		Ttesista tesista = new Ttesista();
		try{
			tperson.setName(data.get("name").toString());
			tperson.setFirstlastname(data.get("firstLastName").toString());
			tperson.setSecondlastname(data.get("secondLastName").toString());
			tperson.setBirthdate(new Date());
			tperson.setModifiedBy(1);
			tperson.setCreatedAt(new Date());
			tperson.setCreatedBy(1);
			tperson.setCreatedAt(new Date());
			tperson.setStatus(1);
			tperson.setGender('M');
			tperson.setModifiedAt(new Date());
			personRepo.save(tperson);
			user.setPassword(data.get("password").toString());
			user.setEmail(data.get("email").toString());
			user.setIdperson(tperson);
			user.setCreatedAt(new Date());
			user.setModifiedAt(new Date());
			user.setCreatedBy(1);
			user.setModifiedBy(1);
			user.setStatus(1);
			LOGGER.debug(">>>Insert()->tuser:{}",user);
			tuserRepository.save(user);
			role.setIduser(user);
			role.setIdrol(new Crole());
			role.getIdrol().setId(7);
			role.setCreatedAt(new Date());
			role.setCreatedBy(1);
			role.setModifiedAt(new Date());
			role.setModifiedBy(1);
			role.setStatus(1);
			roleRepo.save(role);
			tesista.setIdPerson(tperson);
			tesista.setEnrollment(data.get("mat").toString());
			tesista.setIdCatDegree(new Integer(2));
			tesista.setStatus(1);
			tesista.setModifiedBy(1);
			tesista.setCreatedBy(1);
			tesista.setModifiedAt(new Date());
			tesista.setCreatedAt(new Date());
			tesistaRepo.save(tesista);

		}catch(Exception e){
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
