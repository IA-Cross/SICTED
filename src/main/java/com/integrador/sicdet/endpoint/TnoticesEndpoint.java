package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Tnotices;
import com.integrador.sicdet.service.TnoticesService;
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
@RequestMapping("/tnotices")
public class TnoticesEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TnoticesEndpoint.class);

	@Autowired
	private TnoticesService tnoticesService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tnotices tnotices){
		LOGGER.debug(">>>Insert()->tnotices:{}",tnotices);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tnoticesService.insert(tnotices);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBody<Void>> update(@RequestParam("id") int id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tnotices: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tnoticesService.update(id,data);
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
			tnoticesService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Tnotices>>> findAll(@RequestParam("start") int start,@RequestParam("limit") int limit){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",start,limit);
		ResponseEntity<ResponseBody<List<Tnotices>>> response=null;
		List<Tnotices>tnoticesList=null;
		try{
			tnoticesList=tnoticesService.findAll(start,limit);
			System.out.println("Se ecncontro bien");
			response=Utils.<List<Tnotices>>response(HttpStatus.OK,"Lista encontrada",tnoticesList);
		}catch (Exception e){
			response=Utils.<List<Tnotices>>response(HttpStatus.NOT_FOUND,"Lista no encontrada",tnoticesList);
		}
		return response;
	}
}
