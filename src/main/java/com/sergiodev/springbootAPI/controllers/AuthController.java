package com.sergiodev.springbootAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sergiodev.springbootAPI.dao.UsuarioDAO;
import com.sergiodev.springbootAPI.models.UsuarioModel;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody UsuarioModel usuario){
        if(usuarioDAO.verificarCredenciales(usuario)){
            return "OK";
        }
        return "FAIL";

    }

}
