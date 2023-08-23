package com.sergiodev.springbootAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sergiodev.springbootAPI.dao.UsuarioDAO;
import com.sergiodev.springbootAPI.models.UsuarioModel;
import com.sergiodev.springbootAPI.utils.JWTUtil;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private JWTUtil jwtUtil;
    
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody UsuarioModel usuario){
        UsuarioModel usuarioLooged = usuarioDAO.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLooged != null){
            String tokenJWT = jwtUtil.create(String.valueOf(usuarioLooged.getId()), usuarioLooged.getEmail());
            return tokenJWT;
        }
        return "FAIL";
    }

}
