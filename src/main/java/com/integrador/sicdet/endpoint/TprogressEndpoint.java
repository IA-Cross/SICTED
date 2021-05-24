package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Tprogress;
import com.integrador.sicdet.service.TprogressService;
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
@RequestMapping("/tprogress")
public class TprogressEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TprogressEndpoint.class);

	@Autowired
	private TprogressService tprogressService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tprogress tprogress){
		LOGGER.debug(">>>Insert()->tprogress:{}",tprogress);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tprogressService.insert(tprogress);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBody<Void>> update(@RequestParam("id") int id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tprogress: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tprogressService.update(id,data);
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
			tprogressService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Tprogress>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Tprogress>>> response=null;
		List<Tprogress>tprogressList=null;
		try{
			tprogressList=tprogressService.findAll(page,size);
			response=Utils.<List<Tprogress>>response(HttpStatus.OK,"Lista encontrada",tprogressList);
		}catch (Exception e){
			response=Utils.<List<Tprogress>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tprogressList);
		}
		return response;
	}
	
	@GetMapping("/findAllByIdTesis")
	public ResponseEntity<ResponseBody<List<Tprogress>>> findAllByIdTesis(@RequestParam("idTesis") int idTesis){
		ResponseEntity<ResponseBody<List<Tprogress>>> response = null;
		List<Tprogress> tprogressList = null;
		try {
			tprogressList = tprogressService.findAllByIdTesis(idTesis);
			response = Utils.<List<Tprogress>>response(HttpStatus.OK,"Lista encontrada",tprogressList);
		} catch (Exception e) {
			response = Utils.<List<Tprogress>>response(HttpStatus.NOT_FOUND,"Lista no encontrada",tprogressList);
		}
		return response;
	}
}
