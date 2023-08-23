package com.sergiodev.springbootAPI.dao;

import java.util.List;

import com.sergiodev.springbootAPI.models.UsuarioModel;

public interface UsuarioDAO {
    
    List<UsuarioModel> getUsuarios();
    UsuarioModel getUsuario(Long id);
    void eliminarUsuario(Long id);
    void registrarUsuario(UsuarioModel usuario);
    UsuarioModel obtenerUsuarioPorCredenciales(UsuarioModel usuario);
}
