package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Crole;
import com.integrador.sicdet.service.CroleService;
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
@RequestMapping("/crole")
public class CroleEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(CroleEndpoint.class);

	@Autowired
	private CroleService croleService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Crole crole){
		LOGGER.debug(">>>Insert()->crole:{}",crole);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			croleService.insert(crole);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, crole: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			croleService.update(id,data);
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
			croleService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Crole>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Crole>>> response=null;
		List<Crole>croleList=null;
		try{
			croleList=croleService.findAll(page,size);
			response=Utils.<List<Crole>>response(HttpStatus.OK,"Lista encontrada",croleList);
		}catch (Exception e){
			response=Utils.<List<Crole>>response(HttpStatus.NOT_FOUND,"Lista encontrada",croleList);
		}
		return response;
	}
}
