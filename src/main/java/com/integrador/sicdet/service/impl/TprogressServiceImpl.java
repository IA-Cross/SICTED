package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tprogress;
import com.integrador.sicdet.repository.TprogressRepository;
import com.integrador.sicdet.service.TprogressService;
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
public class TprogressServiceImpl implements TprogressService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TprogressServiceImpl.class);

	@Autowired
	private TprogressRepository tprogressRepository;

	@Override
	public void insert(Tprogress tprogress ) throws Exception{
		LOGGER.debug(">>>Insert()->tprogress:{}",tprogress);
		try{
			tprogressRepository.save(tprogress);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tprogress: {}",id,data);
		try{
			Optional<Tprogress> tprogressOptional = tprogressRepository.findById(id);
			if(!tprogressOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//ttesisId
			if(data.containsKey("ttesisId")){
				Integer ttesisId = (Integer)data.get("ttesisId");
				tprogressOptional.get().setTtesisId(ttesisId);
			}
			//progress
			if(data.containsKey("progress")){
				Integer progress = (Integer)data.get("progress");
				tprogressOptional.get().setProgress(progress);
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				tprogressOptional.get().setDescription(description);
			}
			//isVerified
			if(data.containsKey("isVerified")){
				Integer isVerified = (Integer)data.get("isVerified");
				tprogressOptional.get().setIsVerified(isVerified);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tprogressOptional.get().setStatus(status);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tprogressOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tprogressOptional.get().setModifiedBy(modifiedBy);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tprogressOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tprogressOptional.get().setCreatedBy(createdBy);
			}
			tprogressRepository.save(tprogressOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tprogress> tprogressOptional = tprogressRepository.findById(id);
			if(!tprogressOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tprogressRepository.delete(tprogressOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tprogress> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tprogress>tprogressList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tprogressList = tprogressRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tprogressList: {}",tprogressList);
		return tprogressList;
	}

}
