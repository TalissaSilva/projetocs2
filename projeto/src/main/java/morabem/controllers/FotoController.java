package morabem.controllers;

import morabem.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FotoController {

    @Autowired
    public StorageService storageService;

    @GetMapping(path = "/fotos/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFoto(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }
}
