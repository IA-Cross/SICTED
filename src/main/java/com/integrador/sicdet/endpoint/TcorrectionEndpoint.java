package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Tcorrection;
import com.integrador.sicdet.service.TcorrectionService;
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
@RequestMapping("/tcorrection")
public class TcorrectionEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TcorrectionEndpoint.class);

	@Autowired
	private TcorrectionService tcorrectionService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tcorrection tcorrection){
		LOGGER.debug(">>>Insert()->tcorrection:{}",tcorrection);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tcorrectionService.insert(tcorrection);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tcorrection: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tcorrectionService.update(id,data);
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
			tcorrectionService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Tcorrection>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Tcorrection>>> response=null;
		List<Tcorrection>tcorrectionList=null;
		try{
			tcorrectionList=tcorrectionService.findAll(page,size);
			response=Utils.<List<Tcorrection>>response(HttpStatus.OK,"Lista encontrada",tcorrectionList);
		}catch (Exception e){
			response=Utils.<List<Tcorrection>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tcorrectionList);
		}
		return response;
	}
}
