package com.integrador.sicdet.endpoint;

import com.integrador.sicdet.entity.Tuser;
import com.integrador.sicdet.entity.TuserWithRolesFormat;
import com.integrador.sicdet.service.TuserService;
import com.integrador.sicdet.config.ResponseBody;
import com.integrador.sicdet.config.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/tuser")
public class TuserEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TuserEndpoint.class);

	@Autowired
	private TuserService tuserService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tuser tuser){
		LOGGER.debug(">>>Insert()->tuser:{}",tuser);
		Tuser user = tuser;
		user.setCreatedAt(new Date());
		user.setModifiedAt(new Date());
		user.setCreatedBy(1);
		user.setModifiedBy(1);
		System.out.println(user);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tuserService.insert(user);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}
	
	@PostMapping("/insert2")
	public ResponseEntity<ResponseBody<Void>> insert(){
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto correctamente el usuario",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el usuario",null);
		}
	return response;
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBody<Void>> update(@RequestParam("id") int id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tuser: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tuserService.update(id,data);
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
			tuserService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<TuserWithRolesFormat>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<TuserWithRolesFormat>>> response=null;
		List<TuserWithRolesFormat>tuserList=null;
		try{
			tuserList=tuserService.findAll(page,size);
			response=Utils.<List<TuserWithRolesFormat>>response(HttpStatus.OK,"Lista encontrada",tuserList);
		}catch (Exception e){
			response=Utils.<List<TuserWithRolesFormat>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tuserList);
		}
		return response;
	}
	
	@GetMapping("/findUserById")
	public ResponseEntity<ResponseBody<Tuser>> findUserById(@RequestParam("id") int id){
		LOGGER.debug(">>>>> findUserById <<<<<<<< id: {}", id);
		Tuser user = null;
		ResponseEntity<ResponseBody<Tuser>> response = null;
		try {
			user = tuserService.findUserById(id);
			response = Utils.<Tuser>response(HttpStatus.OK,"Usuario encontrado",user);
		} catch (Exception e) {
			response = Utils.<Tuser>response(HttpStatus.NOT_FOUND,"Usuario no encontrado", user);
		}
		return response;
	}

	@GetMapping("/searchUsersByEmail")
	public ResponseEntity<ResponseBody<List<Tuser>>>searchUser(@RequestParam("email") String email){
		LOGGER.debug(">>>>>>searchUser<<<<<< name: {}", email);
		List<Tuser> user = null;
		ResponseEntity<ResponseBody<List<Tuser>>> response = null;
		try {
			user = tuserService.searchUser(email);
			response = Utils.<List<Tuser>>response(HttpStatus.OK,"Usuario encontrado",user);
		}catch(Exception e){
			response= Utils.<List<Tuser>>response(HttpStatus.NOT_FOUND,"Usuario no encontrado", user);
		}
		return response;
	}
}
