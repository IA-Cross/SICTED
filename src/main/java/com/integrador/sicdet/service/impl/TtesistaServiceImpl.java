package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tperson;
import com.integrador.sicdet.entity.Ttesis;
import com.integrador.sicdet.entity.Ttesista;
import com.integrador.sicdet.repository.TtesistaRepository;
import com.integrador.sicdet.service.TtesistaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TtesistaServiceImpl implements TtesistaService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TtesistaServiceImpl.class);

	@Autowired
	private TtesistaRepository ttesistaRepository;

	@Override
	public void insert(Ttesista ttesista ) throws Exception{
		LOGGER.debug(">>>Insert()->ttesista:{}",ttesista);
		ttesista.setCreatedAt(new Date());
		ttesista.setModifiedAt(new Date());
		ttesista.setCreatedBy(1);
		ttesista.setModifiedBy(1);
		ttesista.setStatus(1);
		try{
			ttesistaRepository.save(ttesista);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, ttesista: {}",id,data);
		try{
			Ttesista ttesistaOptional = ttesistaRepository.findById(id).get();
			if(ttesistaOptional == null){
				throw new Exception("No existe el registro");
			}
			if(ttesistaOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//idPerson
			if(data.containsKey("idPerson")){
				ttesistaOptional.setIdPerson(new Tperson());
				ttesistaOptional.getIdPerson().setId((Integer)data.get("idPerson"));
			}
			//ttesisId
			if(data.containsKey("ttesisId")){
				ttesistaOptional.setTtesisId(new Ttesis());
				ttesistaOptional.getTtesisId().setId((Integer)data.get("ttesisId"));
			}
			//enrollment
			if(data.containsKey("enrollment")){
				String enrollment = data.get("enrollment").toString();
				ttesistaOptional.setEnrollment(enrollment);
			}
			//idCatDegree
			if(data.containsKey("idCatDegree")){
				Integer idCatDegree = (Integer)data.get("idCatDegree");
				ttesistaOptional.setIdCatDegree(idCatDegree);
			}
			//yearStart
			if(data.containsKey("yearStart")){
				Date yearStart = (Date)data.get("yearStart");
				ttesistaOptional.setYearStart(yearStart);
			}
			//yearEnd
			if(data.containsKey("yearEnd")){
				Date yearEnd = (Date)data.get("yearEnd");
				ttesistaOptional.setYearEnd(yearEnd);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				ttesistaOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				ttesistaOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				ttesistaOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				ttesistaOptional.setModifiedBy(modifiedBy);
			}
			ttesistaRepository.save(ttesistaOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Ttesista ttesistaOptional = ttesistaRepository.findById(id).get();
			if(ttesistaOptional == null){
				throw new Exception("No existe el registro");
			}
			if(ttesistaOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			ttesistaOptional.setStatus(0);
			ttesistaRepository.save(ttesistaOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Ttesista> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Ttesista>ttesistaList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			ttesistaList = ttesistaRepository.findAllActive(pageable);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< ttesistaList: {}",ttesistaList);
		return ttesistaList;
	}

	@Override
	public List<Ttesista> searchByEnrrollment(String enrrollment) throws Exception {
		LOGGER.debug(">>>> searchByEnrrollment <<<< enrrollment: {}",enrrollment);
		List<Ttesista>finded=new ArrayList<>();
		try{
			finded= ttesistaRepository.searchByEnrrollment("%"+enrrollment+"%");
		}catch(Exception e){
			LOGGER.error("Exception: {}",e);
		}
		return finded;
	}

}
