package com.sergiodev.springbootAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sergiodev.springbootAPI.dao.UsuarioDAO;
import com.sergiodev.springbootAPI.models.UsuarioModel;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public UsuarioModel getUsuario(@PathVariable Long id){
        return usuarioDAO.getUsuario(id);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<UsuarioModel> getUsuarios(){
        return usuarioDAO.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody UsuarioModel usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        
        usuarioDAO.registrarUsuario(usuario);
    }
    
    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable Long id){
        usuarioDAO.eliminarUsuario(id);
    }
    // TO DO
    @RequestMapping(value = "api/usuarios", method = RequestMethod.PUT)
    public UsuarioModel editarUsuario(){
        UsuarioModel usuario = new UsuarioModel();
        return usuario;
    }

}
