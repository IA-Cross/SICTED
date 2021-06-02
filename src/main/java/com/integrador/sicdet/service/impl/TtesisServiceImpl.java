package com.integrador.sicdet.service.impl;

import com.integrador.sicdet.entity.*;
import com.integrador.sicdet.repository.TtesisRepository;
import com.integrador.sicdet.repository.TtesistaRepository;
import com.integrador.sicdet.service.TtesisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import java.util.stream.Collectors;

@Service
public class TtesisServiceImpl implements TtesisService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TtesisServiceImpl.class);

	@Autowired
	private TtesisRepository ttesisRepository;
	@Autowired
	private TtesistaRepository tesistaRepo;

	@Override
	public void insert(Ttesis ttesis ) throws Exception{
		LOGGER.debug(">>>Insert()->ttesis:{}",ttesis);
		try{
			ttesis.setId(0);
			ttesis.setCreatedAt(new Date());
			ttesis.setModifiedAt(new Date());
			ttesis.setCreatedBy(1);
			ttesis.setModifiedBy(1);
			ttesis.setStatus(1);
			LOGGER.debug(">>>Insert()->tasesor:{}",ttesis);
			ttesisRepository.save(ttesis);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	
	@Override
	public void updateUrl(Integer id, String url) throws Exception{
		LOGGER.debug(">>>> update->id: {}, ttesis: {}",id,url);
		try{
			Ttesis ttesisOptional = ttesisRepository.findById(id).get();
			if(ttesisOptional == null){
				throw new Exception("No existe el registro");
			}
			if(ttesisOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			ttesisOptional.setUrl(url);
			ttesisRepository.save(ttesisOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, ttesis: {}",id,data);
		try{
			Ttesis ttesisOptional = ttesisRepository.findById(id).get();
			if(ttesisOptional == null){
				throw new Exception("No existe el registro");
			}
			if(ttesisOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			//idAsesor
			if(data.containsKey("idAsesor")){
				ttesisOptional.setIdAsesor(new Tasesor());
				ttesisOptional.getIdAsesor().setId((Integer)data.get("idAsesor"));
			}
			//idCatDegree
			if(data.containsKey("idCatDegree")){
				Integer idCatDegree = (Integer)data.get("idCatDegree");
				ttesisOptional.setIdCatDegree(idCatDegree);
			}
			//idTcatalog
			if(data.containsKey("idTcatalog")){
				Integer idTcatalog = (Integer)data.get("idTcatalog");
				ttesisOptional.setIdTcatalog(idTcatalog);
			}
			//yearStart
			if(data.containsKey("yearStart")){
				Date yearStart = (Date)data.get("yearStart");
				ttesisOptional.setYearStart(yearStart);
			}
			//keywords
			if(data.containsKey("keywords")){
				String keywords = data.get("keywords").toString();
				ttesisOptional.setKeywords(keywords);
			}
			//categoria
			if(data.containsKey("categoria")){
				String categoria = data.get("categoria").toString();
				ttesisOptional.setCategoria(categoria);
			}
			//url
			if(data.containsKey("url")){
				String url = data.get("url").toString();
				ttesisOptional.setUrl(url);
			}
			//isPublished
			if(data.containsKey("isPublished")){
				Integer isPublished = (Integer)data.get("isPublished");
				ttesisOptional.setIsPublished(isPublished);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				ttesisOptional.setCreatedBy(createdBy);
			}
			//createdAt
			if(data.containsKey("createdAt")){
				Date createdAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("createdAt"));
				ttesisOptional.setCreatedAt(createdAt);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = new SimpleDateFormat("yyyy-MM-dd").parse((String) data.get("modifiedAt"));
				ttesisOptional.setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				ttesisOptional.setModifiedBy(modifiedBy);
			}

			if(data.containsKey("title")){
				String title = data.get("title").toString();
				ttesisOptional.setTitle(title);
			}
			ttesisRepository.save(ttesisOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Ttesis ttesisOptional = ttesisRepository.findById(id).get();
			if(ttesisOptional == null){
				throw new Exception("No existe el registro");
			}
			if(ttesisOptional.getStatus() == 0) {
				throw new Exception("No existe el registro");				
			}
			ttesisOptional.setStatus(0);
			ttesisRepository.save(ttesisOptional);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Ttesis> findAll() throws Exception{
		LOGGER.debug(">>>> findAll <<<<");
		List<Ttesis>ttesisList=null;
		try{
			ttesisList = ttesisRepository.findAllActive();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< ttesisList: {}",ttesisList);
		return ttesisList;
	}

	@Override
	public List<Ttesis> searchTesis(int start, int limit, Map<String, Object> data) throws Exception {
		LOGGER.debug(">>>> update->id: {}, ttesis: {}",data);
		List<Ttesis>ttesisList=new ArrayList<>();
		List<Ttesis>ttesisList2=new ArrayList<>();
		List<Ttesis>ttesisListFinal=new ArrayList<>();
		Ttesista tesista = null;
		try{
				String title =null;
				int advisor=0;
				String author=null;
				 boolean existTitle=false;
				boolean existAdvisor=false;
				boolean existAuthor=false;
			if((!data.get("advisor").toString().equals("")||data.get("advisor").toString().equals("undefined"))){
				existAdvisor=true;
				advisor = Integer.parseInt(data.get("advisor").toString());
			}
			if((!data.get("title").toString().equals("")||data.get("title").toString().equals("undefined"))){
				existTitle=true;
				title = data.get("title").toString();
			}
			if((!data.get("author").toString().equals("")||data.get("author").toString().equals("undefined"))){
				existAuthor=true;
				author= data.get("author").toString();
			}
			//Se busca por titulo y asesor
			if(existAdvisor && existTitle) {
				ttesisList = ttesisRepository.searchTesis('%' + title + '%', advisor);
			}
			else if(existAdvisor){
				ttesisList=ttesisRepository.findByAdvisor(advisor);
			}
			else if(existTitle) {
				ttesisList = ttesisRepository.findByTitle(title);
			}
			if(existAuthor) {
				//Se busca por autor
				tesista = tesistaRepo.findByName('%' + author + '%');
				Ttesis tesistaTesis = tesista.getTtesisId();
				if(existAuthor && !existAdvisor && !existTitle){
					ttesisList.add(tesistaTesis);
				}else {
					ttesisList2 = ttesisList.stream().filter(t -> t.getTitle().equals(tesistaTesis.getTitle())).collect(Collectors.toList());
					ttesisList = ttesisList2;
				}
			}

			for (int i=start; i<ttesisList.size()&&ttesisListFinal.size()<limit;i++){
				ttesisListFinal.add(ttesisList.get(i));
			}
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.info("Resultados: {}",ttesisListFinal);
		return ttesisListFinal;
	}

	@Override
	public List<TesisCardFormat> findAllCardFormat() throws Exception {
		LOGGER.debug(">>>> findAll <<<<");
		List<Ttesis>ttesisList=null;
		List<TesisCardFormat> cards= new ArrayList<>();
		List<TesisCardFormat> cardsRes= new ArrayList<>();
		Ttesista findedTesist = new Ttesista();
		TesisCardFormat card = null;
		String name="";
		try{
			ttesisList = ttesisRepository.findAllActive();
			for (Ttesis tesis : ttesisList) {
				findedTesist= tesistaRepo.findByIdTesis(tesis.getId());
				name = findedTesist.getIdPerson().getName()+" "+findedTesist.getIdPerson().getFirstlastname()+" "+findedTesist.getIdPerson().getSecondlastname();
				card = TesisCardBuilder.fromTesis(tesis, name);
				cards.add(card);
			}

			for (int i=0; i<cards.size()&&cardsRes.size()<3;i++){
				cardsRes.add(cards.get(i));
			}

		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< ttesisList: {}",ttesisList);
		return cardsRes;
	}
	
	@Override
	public Ttesis findById(int id) throws Exception {
		LOGGER.debug(">>>> findById <<<<");
		Ttesis tesis = null;
		try {
			tesis = ttesisRepository.findById(id);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tesis encontrada: {}",tesis);
		return tesis;
	}
	@Override
	public Ttesis searchTesisByTitle(String titulo) throws Exception {
		LOGGER.debug(">>>> searchTesisByTitle <<<<");
		Ttesis tesis = null;
		try {
			tesis = ttesisRepository.searchTesisByTitle(titulo);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tesis encontrada: {}",tesis);
		return tesis;
	}

	@Override
	public List<Ttesis> findAdvisedTesis(int id) {
		List<Ttesis> res = null;
		try{
			res = ttesisRepository.findByIdAsesor(id);
		}catch(Exception e){
			LOGGER.error("Exception: {}",e);
		}
		return res;
	}


}
