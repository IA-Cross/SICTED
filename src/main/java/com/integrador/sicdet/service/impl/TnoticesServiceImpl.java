package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tnotices;
import com.integrador.sicdet.repository.TnoticesRepository;
import com.integrador.sicdet.service.TnoticesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TnoticesServiceImpl implements TnoticesService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TnoticesServiceImpl.class);

	@Autowired
	private TnoticesRepository tnoticesRepository;

	@Override
	public void insert(Tnotices tnotices ) throws Exception{
		LOGGER.debug(">>>Insert()->tnotices:{}",tnotices);
		try{
			tnotices.setCreatedAt(new Date());
			tnotices.setModifiedAt(new Date());
			tnotices.setCreatedBy(1);
			tnotices.setModifiedBy(1);
			tnotices.setStatus(1);
			LOGGER.debug(">>>Insert()->tnotices:{}",tnotices);
			tnoticesRepository.save(tnotices);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tnotices: {}",id,data);
		try{
			Tnotices tnoticesOptional = tnoticesRepository.findById(id).get();
			if(tnoticesOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tnoticesOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//title
			if(data.containsKey("title")){
				String title = data.get("title").toString();
				tnoticesOptional.setTitle(title);
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				tnoticesOptional.setDescription(description);
			}
			//urlimagen
			if(data.containsKey("urlimagen")){
				String urlimagen = data.get("urlimagen").toString();
				tnoticesOptional.setUrlimagen(urlimagen);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				tnoticesOptional.setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tnoticesOptional.setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				tnoticesOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tnoticesOptional.setModifiedBy(modifiedBy);
			}
			tnoticesRepository.save(tnoticesOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tnotices tnoticesOptional = tnoticesRepository.findById(id).get();
			if(tnoticesOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tnoticesOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tnoticesOptional.setStatus(0);
			tnoticesRepository.save(tnoticesOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tnotices> findAll(int start,int limit) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",start,limit);
		List<Tnotices>tnoticesList=null;
		List<Tnotices>responseList=new ArrayList<>();
		try{
			tnoticesList = tnoticesRepository.findAllActive();
			for (int i=start; i<tnoticesList.size()&&responseList.size()<limit;i++){
				responseList.add(tnoticesList.get(i));
			}
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tnoticesList: {}",responseList);
		return responseList;
	}

}
