package com.integrador.sicdet.uploadfile.endpoint;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.integrador.sicdet.uploadfile.utils.FileManage;

@Controller
@RequestMapping("/fileupload")
public class UploadEndpoint {

	@RequestMapping(value = "/file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String myService(@RequestParam("file") MultipartFile file) throws Exception {

		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		if (!file.isEmpty()) {
			FileManage.saveFile("tmp" + FileManage.obtenerSeparadorRutaPorServidor(), file.getOriginalFilename(),
					file.getInputStream());
		}
		System.out.println(FileManage.getFileRoute("tmp", "Nuevo1.jpg"));
		return "success";
	}
}
