package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.TesisCardFormat;
import com.integrador.sicdet.entity.Ttesis;
import com.integrador.sicdet.service.TtesisService;
import com.integrador.sicdet.config.ResponseBody;
import com.integrador.sicdet.config.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
@RestController
@RequestMapping("/ttesis")
public class TtesisEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TtesisEndpoint.class);

	@Autowired
	private TtesisService ttesisService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Ttesis ttesis){
		LOGGER.debug(">>>Insert()->ttesis:{}",ttesis);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			ttesisService.insert(ttesis);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, ttesis: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			ttesisService.update(id,data);
			response= Utils.<Void>response(HttpStatus.OK,"Se actualizo el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo actualizar el registro",null);
		}
	return response;
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseBody<Void>> delete(@PathVariable Integer id){
		LOGGER.debug(">>>> delete->id: {}",id);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			ttesisService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Ttesis>>> findAll(){
		LOGGER.debug(">>>> findAll <<<<");
		ResponseEntity<ResponseBody<List<Ttesis>>> response=null;
		List<Ttesis>ttesisList=null;
		try{
			ttesisList=ttesisService.findAll();
			response=Utils.<List<Ttesis>>response(HttpStatus.OK,"Lista encontrada",ttesisList);
		}catch (Exception e){
			response=Utils.<List<Ttesis>>response(HttpStatus.NOT_FOUND,"Lista encontrada",ttesisList);
		}
		return response;
	}

	@PostMapping("/searchTesis")
	public ResponseEntity<ResponseBody<List<Ttesis>>>searchTesis(@RequestParam("page") int page,@RequestParam("size") int size, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);

		ResponseEntity<ResponseBody<List<Ttesis>>> response=null;
		List<Ttesis>ttesisList=null;
		try{
			ttesisList=ttesisService.searchTesis(page,size,data);
			response=Utils.<List<Ttesis>>response(HttpStatus.OK,"Lista encontrada",ttesisList);
		}catch (Exception e){
			response=Utils.<List<Ttesis>>response(HttpStatus.NOT_FOUND,"Lista encontrada",ttesisList);
		}
		return response;
	}

	@GetMapping("/findAllCardFormat")
	public ResponseEntity<ResponseBody<List<TesisCardFormat>>> findAllCardFormat(){
		LOGGER.debug(">>>> findAll <<<<");
		ResponseEntity<ResponseBody<List<TesisCardFormat>>> response=null;
		List<TesisCardFormat>ttesisList=null;
		try{
			ttesisList=ttesisService.findAllCardFormat();
			response=Utils.<List<TesisCardFormat>>response(HttpStatus.OK,"Lista encontrada",ttesisList);
		}catch (Exception e){
			response=Utils.<List<TesisCardFormat>>response(HttpStatus.NOT_FOUND,"Lista encontrada",ttesisList);
		}
		return response;
	}
	
	@GetMapping("/findById")
	public ResponseEntity<ResponseBody<Ttesis>> findById(@RequestParam Map<String,String>params){
		LOGGER.debug(">>>> findById <<<<");
		ResponseEntity<ResponseBody<Ttesis>> response = null;
		Ttesis tesis = null;
		try {
			int id = Integer.parseInt(params.get("id"));
			tesis = ttesisService.findById(id);
			response = Utils.<Ttesis>response(HttpStatus.OK,"Tesis encontrada",tesis);
		} catch (Exception e){
			response = Utils.<Ttesis>response(HttpStatus.NOT_FOUND,"Tesis encontrada",tesis);
		}
		return response;
	}
	
	@GetMapping("/searchTesisByTitle")
	public ResponseEntity<ResponseBody<Ttesis>> searchTesisByTitle(@RequestParam Map<String,String>params){
		LOGGER.debug(">>>> findById <<<<");
		ResponseEntity<ResponseBody<Ttesis>> response = null;
		Ttesis tesis = null;
		try {
			String titulo = params.get("titulo");
			tesis = ttesisService.searchTesisByTitle(titulo);
			response = Utils.<Ttesis>response(HttpStatus.OK,"Tesis encontrada",tesis);
		} catch (Exception e){
			response = Utils.<Ttesis>response(HttpStatus.NOT_FOUND,"Tesis no encontrada",tesis);
		}
		return response;
	}

	@GetMapping("/findAdvisedTesis")
	public ResponseEntity<ResponseBody<List<Ttesis>>> findAdvisedTesis(@RequestParam("idAsesor") int idAsesor){
		LOGGER.debug(">>>> findById <<<<");
		ResponseEntity<ResponseBody<List<Ttesis>>> response = null;
		List<Ttesis> tesis = null;
		try {
			tesis = ttesisService.findAdvisedTesis(idAsesor);
			response = Utils.<List<Ttesis>>response(HttpStatus.OK,"Tesis encontrada",tesis);
		} catch (Exception e){
			response = Utils.<List<Ttesis>>response(HttpStatus.NOT_FOUND,"Tesis no encontrada",tesis);
		}
		return response;
	}
}
