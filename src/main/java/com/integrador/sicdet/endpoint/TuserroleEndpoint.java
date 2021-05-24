package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Tuserrole;
import com.integrador.sicdet.service.TuserroleService;
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
@RequestMapping("/tuserrole")
public class TuserroleEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TuserroleEndpoint.class);

	@Autowired
	private TuserroleService tuserroleService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tuserrole tuserrole){
		LOGGER.debug(">>>Insert()->tuserrole:{}",tuserrole);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tuserroleService.insert(tuserrole);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBody<Void>> update(@RequestParam("id") int id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tuserrole: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tuserroleService.update(id,data);
			response= Utils.<Void>response(HttpStatus.OK,"Se actualizo el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo actualizar el registro",null);
		}
	return response;
	}

	@GetMapping("/delete")
	public ResponseEntity<ResponseBody<Void>> delete(@RequestParam("id") int id){
		LOGGER.debug(">>>> delete->id: {}",id);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tuserroleService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Tuserrole>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Tuserrole>>> response=null;
		List<Tuserrole>tuserroleList=null;
		try{
			tuserroleList=tuserroleService.findAll(page,size);
			response=Utils.<List<Tuserrole>>response(HttpStatus.OK,"Lista encontrada",tuserroleList);
		}catch (Exception e){
			response=Utils.<List<Tuserrole>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tuserroleList);
		}
		return response;
	}
}
