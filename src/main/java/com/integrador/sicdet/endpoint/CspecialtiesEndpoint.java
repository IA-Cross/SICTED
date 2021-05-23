package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Cspecialties;
import com.integrador.sicdet.service.CspecialtiesService;
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
@RequestMapping("/cspecialties")
public class CspecialtiesEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(CspecialtiesEndpoint.class);

	@Autowired
	private CspecialtiesService cspecialtiesService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Cspecialties cspecialties){
		LOGGER.debug(">>>Insert()->cspecialties:{}",cspecialties);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			cspecialtiesService.insert(cspecialties);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBody<Void>> update(@RequestParam("id") int id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, cspecialties: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			cspecialtiesService.update(id,data);
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
			cspecialtiesService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Cspecialties>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Cspecialties>>> response=null;
		List<Cspecialties>cspecialtiesList=null;
		try{
			cspecialtiesList=cspecialtiesService.findAll(page,size);
			response=Utils.<List<Cspecialties>>response(HttpStatus.OK,"Lista encontrada",cspecialtiesList);
		}catch (Exception e){
			response=Utils.<List<Cspecialties>>response(HttpStatus.NOT_FOUND,"Lista encontrada",cspecialtiesList);
		}
		return response;
	}
}
