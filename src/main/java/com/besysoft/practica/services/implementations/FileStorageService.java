package com.besysoft.practica.services.implementations;

import com.besysoft.practica.exceptions.DIrectoryCantBeCreatedException;
import com.besysoft.practica.exceptions.EmptyFileException;
import com.besysoft.practica.exceptions.FileCantBeOpenedOrRead;
import com.besysoft.practica.exceptions.StorageFileException;
import com.besysoft.practica.services.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService implements StorageService {


   // @Value("${files.location}")
    private String filesLocation;

    private Path rootLocation;

    @Autowired
    ResourceLoader resourceLoader;
    @Override
    @PostConstruct
    public void init() throws  DIrectoryCantBeCreatedException{
        Resource resource=resourceLoader.getResource("file:mediafiles/");
        //rootLocation=Paths.get(filesLocation);
        try {
            rootLocation=resource.getFile().toPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!rootLocation.toFile().exists()){
            try {
                Files.createDirectory(rootLocation);
            } catch (IOException e) {
                throw new DIrectoryCantBeCreatedException(String.format("No se puede crear la carpeta %s",rootLocation),e);
            }
        }

    }

    @Override
    public String store(MultipartFile file) throws StorageFileException,EmptyFileException {
        String fileUrl="";
        if(file.isEmpty()){
            throw new EmptyFileException("No se ha recibido ningún archivo");
        }
        String fileName=file.getOriginalFilename();
        Path pathToBeStored=rootLocation.resolve(Paths.get(fileName))
                .normalize().toAbsolutePath();
        try(InputStream is=file.getInputStream()){
            Files.copy(is,pathToBeStored, StandardCopyOption.REPLACE_EXISTING);
            fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/mediafiles/")
                    .path(fileName)
                    .toUriString();
        } catch (IOException e) {
            throw new StorageFileException("No se pudo guardar el archivo",e);
        }
        return fileUrl;
    }

    @Override
    public Resource loadAsResource(String fileName)throws FileCantBeOpenedOrRead {
        Path ruta=rootLocation.resolve(fileName);

        try {
            Resource resource=new UrlResource((ruta.toUri()));
            if(resource.exists() && resource.isReadable()){
                return resource;
            }else{
                throw  new FileCantBeOpenedOrRead(String.format("No se puede leer el archivo %s",fileName));
            }

        } catch (MalformedURLException e) {
            throw new FileCantBeOpenedOrRead(String.format("No se puede leer el archivo %s",fileName),e);
        }

    }
}