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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.Date;

@Service
public class TtesistaServiceImpl implements TtesistaService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TtesistaServiceImpl.class);

	@Autowired
	private TtesistaRepository ttesistaRepository;

	@Override
	public void insert(Ttesista ttesista ) throws Exception{
		LOGGER.debug(">>>Insert()->ttesista:{}",ttesista);
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
			Optional<Ttesista> ttesistaOptional = ttesistaRepository.findById(id);
			if(!ttesistaOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//idPerson
			if(data.containsKey("idPerson")){
				ttesistaOptional.get().setIdPerson(new Tperson());
				ttesistaOptional.get().getIdPerson().setId((Integer)data.get("idPerson"));
			}
			//ttesisId
			if(data.containsKey("ttesisId")){
				ttesistaOptional.get().setTtesisId(new Ttesis());
				ttesistaOptional.get().getTtesisId().setId((Integer)data.get("ttesisId"));
			}
			//enrollment
			if(data.containsKey("enrollment")){
				String enrollment = data.get("enrollment").toString();
				ttesistaOptional.get().setEnrollment(enrollment);
			}
			//idCatDegree
			if(data.containsKey("idCatDegree")){
				Integer idCatDegree = (Integer)data.get("idCatDegree");
				ttesistaOptional.get().setIdCatDegree(idCatDegree);
			}
			//yearStart
			if(data.containsKey("yearStart")){
				Date yearStart = (Date)data.get("yearStart");
				ttesistaOptional.get().setYearStart(yearStart);
			}
			//yearEnd
			if(data.containsKey("yearEnd")){
				Date yearEnd = (Date)data.get("yearEnd");
				ttesistaOptional.get().setYearEnd(yearEnd);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				ttesistaOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				ttesistaOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				ttesistaOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				ttesistaOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				ttesistaOptional.get().setModifiedBy(modifiedBy);
			}
			ttesistaRepository.save(ttesistaOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Ttesista> ttesistaOptional = ttesistaRepository.findById(id);
			if(!ttesistaOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			ttesistaRepository.delete(ttesistaOptional.get());
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
			ttesistaList = ttesistaRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< ttesistaList: {}",ttesistaList);
		return ttesistaList;
	}

}
