package com.integrador.sicdet.uploadfile.utils;

import org.apache.commons.lang3.SystemUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;

public class FileManage {
    public static String saveFile(String basepath, String filename, InputStream in) throws IOException {
        File folderArchvo = new File(obtenerRutaPorServidor() + basepath);
        if (!folderArchvo.exists()) {
            folderArchvo.mkdirs();
        }
        String finalpath = obtenerRutaPorServidor() + basepath + normalizer(filename);
        final File file = new File(finalpath);
        try (OutputStream out = new FileOutputStream(file)) {
            IOUtils.copy(in, out);
        }
        return basepath + normalizer(filename);
    }

    public static String getFileRoute(String basepath, String filename) {
        return obtenerRutaPorServidor() + basepath +obtenerSeparadorRutaPorServidor()+ normalizer(filename);
    }

    public static String normalizer(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("-", " ")
                .replaceAll("/", " ")
                .replace("\\", " ");
    }

    public static InputStream getFileType(String ruta) throws FileNotFoundException {
        ruta = obtenerRutaParaObtenerArchivo(ruta);
        InputStream input = new FileInputStream(new File(ruta));
        return input;
    }

    public static String obtenerRutaParaObtenerArchivo(String ruta) {
        if(ruta.contains("/")){
            ruta = (ruta.contains("/")) ? ruta.replace("/", FileManage.obtenerSeparadorRutaPorServidor()) : ruta;
        }
        if(ruta.contains("\\")){
            ruta = (ruta.contains("\\")) ? ruta.replace("\\", FileManage.obtenerSeparadorRutaPorServidor()) : ruta;
        }
        return ruta;
    }

    public static void eliminarArchivo(String rutaArchivo) throws IOException {
        Files.deleteIfExists(Paths.get(obtenerRutaPorServidor() + rutaArchivo));
    }
    public static void deleteDirectory(String route)throws IOException{
        File fileDel=new File(route);
        if(fileDel.isDirectory()){

            if(fileDel.list().length == 0)
                fileDel.delete();
            else{

                for (String temp : fileDel.list()) {
                    File fileDelete = new File(fileDel, temp);
                    //recursive delete
                    deleteDirectory(route);
                }

                //check the directory again, if empty then delete it
                if(fileDel.list().length==0)
                    fileDel.delete();

            }

        }else{
            //if file, then delete it
            fileDel.delete();
        }
    }
    
    public static String obtenerRutaPorServidor() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return "C:\\dce\\";
        } else if (SystemUtils.IS_OS_LINUX) {
            return "/dce/";
        } else if (SystemUtils.IS_OS_MAC) {
            return "/dce/";
        }
        return "";
    }

    public static String obtenerSeparadorRutaPorServidor() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return "\\";
        } else if (SystemUtils.IS_OS_LINUX) {
            return "/";
        } else if (SystemUtils.IS_OS_MAC) {
            return "/";
        }
        return "\\";
    }
}
