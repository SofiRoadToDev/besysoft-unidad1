package com.besysoft.practica.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();
    String store(MultipartFile file);

    Resource loadAsResource(String fileName);
}
