package com.besysoft.practica.controllers;

import com.besysoft.practica.exceptions.EmptyFileException;
import com.besysoft.practica.exceptions.FileCantBeOpenedOrRead;
import com.besysoft.practica.exceptions.StorageFileException;
import com.besysoft.practica.services.interfaces.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("storage")
@AllArgsConstructor
@Log4j2
public class FilesController {

    private final StorageService storageService;
    private final HttpServletRequest servletRequest;

    @PostMapping("/upload")
    public ResponseEntity<?>storageFile(@RequestParam("file")MultipartFile multipartFile)throws StorageFileException, EmptyFileException {
        String ruta=storageService.store(multipartFile);
        String host=servletRequest.getRequestURL().toString().replace(servletRequest.getRequestURI(),"");
        log.info(String.format("host obtenido: %s",host));
        String url= ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(ruta)
                .toString();
        return ResponseEntity.ok(url);
    }


    @GetMapping("{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource>openFile(@PathVariable String fileName) throws FileCantBeOpenedOrRead, IOException {
        Resource file=storageService.loadAsResource(fileName);
        String contentType= Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,contentType)
                .body(file);
    }


}
