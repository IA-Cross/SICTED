package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tnotices;
import com.integrador.sicdet.repository.TnoticesRepository;
import com.integrador.sicdet.service.TnoticesService;
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
public class TnoticesServiceImpl implements TnoticesService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TnoticesServiceImpl.class);

	@Autowired
	private TnoticesRepository tnoticesRepository;

	@Override
	public void insert(Tnotices tnotices ) throws Exception{
		LOGGER.debug(">>>Insert()->tnotices:{}",tnotices);
		try{
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
			Optional<Tnotices> tnoticesOptional = tnoticesRepository.findById(id);
			if(!tnoticesOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//title
			if(data.containsKey("title")){
				String title = data.get("title").toString();
				tnoticesOptional.get().setTitle(title);
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				tnoticesOptional.get().setDescription(description);
			}
			//urlimagen
			if(data.containsKey("urlimagen")){
				String urlimagen = data.get("urlimagen").toString();
				tnoticesOptional.get().setUrlimagen(urlimagen);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tnoticesOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tnoticesOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tnoticesOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tnoticesOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tnoticesOptional.get().setModifiedBy(modifiedBy);
			}
			tnoticesRepository.save(tnoticesOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tnotices> tnoticesOptional = tnoticesRepository.findById(id);
			if(!tnoticesOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tnoticesRepository.delete(tnoticesOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tnotices> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tnotices>tnoticesList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tnoticesList = tnoticesRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tnoticesList: {}",tnoticesList);
		return tnoticesList;
	}

}
