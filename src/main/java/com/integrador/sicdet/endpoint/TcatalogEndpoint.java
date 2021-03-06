package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Tcatalog;
import com.integrador.sicdet.service.TcatalogService;
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
@RequestMapping("/tcatalog")
public class TcatalogEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TcatalogEndpoint.class);

	@Autowired
	private TcatalogService tcatalogService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tcatalog tcatalog){
		LOGGER.debug(">>>Insert()->tcatalog:{}",tcatalog);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tcatalogService.insert(tcatalog);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tcatalog: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tcatalogService.update(id,data);
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
			tcatalogService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Tcatalog>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Tcatalog>>> response=null;
		List<Tcatalog>tcatalogList=null;
		try{
			tcatalogList=tcatalogService.findAll(page,size);
			response=Utils.<List<Tcatalog>>response(HttpStatus.OK,"Lista encontrada",tcatalogList);
		}catch (Exception e){
			response=Utils.<List<Tcatalog>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tcatalogList);
		}
		return response;
	}
}
