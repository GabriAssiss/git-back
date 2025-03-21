package com.cit.backend.api.controller;

//import com.cit.backend.domain.service.UploadFilesService;
import com.cit.backend.exceptions.MissingVariableException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;
import com.cit.backend.domain.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@RestController()
@RequestMapping("/example")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping()
    public String helloWorld() {
        return "Ola seja bem vindo ao sistema do <b>C.I.T</b><br>Condomínio<br>Inteligente<br>";
    }

    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloWorldService.helloWorld(name);
    }

    @GetMapping("/excecao")
    public String exception() {
        List<String> formVariables = Arrays.asList(
            "firstName",
            "lastName",
            "email",
            "password",
            "confirmPassword",
            "phoneNumber",
            "address",
            "city",
            "state",
            "zipCode",
            "country"
        );
        throw new MissingVariableException(formVariables);
    }

    @GetMapping("/permission")
    @RolesAllowed("ADMIN")
    public String permission() {
        return "You have permission to access this page";
    }

//    @PostMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile file) {
//        uploadFilesService.store(file);
//        UploadFilesService uploadFilesService = new UploadFilesService("teste");
//        return "Upload realizado com sucesso";
//    }
}