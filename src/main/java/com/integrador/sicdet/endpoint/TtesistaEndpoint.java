package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Ttesista;
import com.integrador.sicdet.service.TtesistaService;
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
@RequestMapping("/ttesista")
public class TtesistaEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TtesistaEndpoint.class);

	@Autowired
	private TtesistaService ttesistaService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Ttesista ttesista){
		LOGGER.debug(">>>Insert()->ttesista:{}",ttesista);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			ttesistaService.insert(ttesista);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, ttesista: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			ttesistaService.update(id,data);
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
			ttesistaService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Ttesista>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Ttesista>>> response=null;
		List<Ttesista>ttesistaList=null;
		try{
			ttesistaList=ttesistaService.findAll(page,size);
			response=Utils.<List<Ttesista>>response(HttpStatus.OK,"Lista encontrada",ttesistaList);
		}catch (Exception e){
			response=Utils.<List<Ttesista>>response(HttpStatus.NOT_FOUND,"Lista encontrada",ttesistaList);
		}
		return response;
	}
}
