package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tprogress;
import com.integrador.sicdet.entity.Ttesis;
import com.integrador.sicdet.repository.TprogressRepository;
import com.integrador.sicdet.service.TprogressService;
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
public class TprogressServiceImpl implements TprogressService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TprogressServiceImpl.class);

	@Autowired
	private TprogressRepository tprogressRepository;

	@Override
	public void insert(Tprogress tprogress ) throws Exception{
		LOGGER.debug(">>>Insert()->tprogress:{}",tprogress);
		try{
			tprogress.setCreatedAt(new Date());
			tprogress.setModifiedAt(new Date());
			tprogress.setCreatedBy(1);
			tprogress.setModifiedBy(1);
			tprogress.setStatus(1);
			LOGGER.debug(">>>Insert()->tprogress:{}",tprogress);
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
			Tprogress tprogressOptional = tprogressRepository.findById(id).get();
			if(tprogressOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tprogressOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//ttesisId
			if(data.containsKey("ttesisId")){
				tprogressOptional.setTtesisId(new Ttesis());
				tprogressOptional.getTtesisId().setId((Integer)data.get("ttesisId"));
			}
			//progress
			if(data.containsKey("progress")){
				Integer progress = (Integer)data.get("progress");
				tprogressOptional.setProgress(progress);
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				tprogressOptional.setDescription(description);
			}
			//isVerified
			if(data.containsKey("isVerified")){
				Integer isVerified = (Integer)data.get("isVerified");
				tprogressOptional.setIsVerified(isVerified);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				tprogressOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tprogressOptional.setModifiedBy(modifiedBy);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tprogressOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tprogressOptional.setCreatedBy(createdBy);
			}
			tprogressRepository.save(tprogressOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tprogress tprogressOptional = tprogressRepository.findById(id).get();
			if(tprogressOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tprogressOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tprogressOptional.setStatus(0);
			tprogressRepository.save(tprogressOptional);
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
