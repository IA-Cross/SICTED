package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.Tcorrection;
import com.integrador.sicdet.entity.Ttesis;
import com.integrador.sicdet.repository.TcorrectionRepository;
import com.integrador.sicdet.service.TcorrectionService;
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
public class TcorrectionServiceImpl implements TcorrectionService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TcorrectionServiceImpl.class);

	@Autowired
	private TcorrectionRepository tcorrectionRepository;

	@Override
	public void insert(Tcorrection tcorrection ) throws Exception{
		LOGGER.debug(">>>Insert()->tcorrection:{}",tcorrection);
		try{
			tcorrectionRepository.save(tcorrection);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tcorrection: {}",id,data);
		try{
			Optional<Tcorrection> tcorrectionOptional = tcorrectionRepository.findById(id);
			if(!tcorrectionOptional.isPresent()){
				throw new Exception("No existe el registro"
				);
			}
			//idTesis
			if(data.containsKey("idTesis")){
				tcorrectionOptional.get().setIdTesis(new Ttesis());
				tcorrectionOptional.get().getIdTesis().setId((Integer)data.get("idTesis"));
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				tcorrectionOptional.get().setDescription(description);
			}
			//date
			if(data.containsKey("date")){
				Date date = (Date)data.get("date");
				tcorrectionOptional.get().setDate(date);
			}
			//text
			if(data.containsKey("text")){
				tcorrectionOptional.get().setText(data.get("text").toString());
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				tcorrectionOptional.get().setStatus(status);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = (Date)data.get("createdAt");
				tcorrectionOptional.get().setCreatedAt(createdAt);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tcorrectionOptional.get().setCreatedBy(createdBy);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tcorrectionOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tcorrectionOptional.get().setModifiedBy(modifiedBy);
			}
			tcorrectionRepository.save(tcorrectionOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Tcorrection tcorrectionOptional = tcorrectionRepository.findById(id).get();
			if(tcorrectionOptional == null){
				throw new Exception("No existe el registro");
			}
			if(tcorrectionOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			tcorrectionOptional.setStatus(0);
			tcorrectionRepository.save(tcorrectionOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tcorrection> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tcorrection>tcorrectionList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tcorrectionList = tcorrectionRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tcorrectionList: {}",tcorrectionList);
		return tcorrectionList;
	}

	@Override
	public List<Tcorrection> getCorrectionsByIdTesis(int page, int size, int idTesis) throws Exception {
		List<Tcorrection> res=null;
		Pageable pageable= PageRequest.of(page,size);
		try {
			res= tcorrectionRepository.findByIdTesis(idTesis,pageable);
		}catch(Exception e){
			LOGGER.error("Exception: {}",e);
		}
		LOGGER.debug(">>>> findAll <<<< res: {}",res);

		return res;
	}

}
